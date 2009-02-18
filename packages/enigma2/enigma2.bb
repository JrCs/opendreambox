DESCRIPTION = "Enigma2 is an experimental, but useful framebuffer-based frontend for DVB functions"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
DEPENDS = "jpeg libungif libmad libpng libsigc++-1.2 gettext-native \
	dreambox-dvbincludes freetype libdvbsi++ python swig-native  \
	gstreamer gst-plugins-good gst-plugins-bad gst-plugins-ugly \
	gst-plugin-dvbmediasink gst-plugin-fluendo-mpegdemux \
	libfribidi libxmlccwrap libdreamdvd tuxbox-tuxtxt-32bpp"
RDEPENDS = "python-codecs python-core python-datetime python-elementtree \
	python-fcntl python-io python-math python-lang python-re \
	python-stringold python-threading python-xml gst-plugin-decodebin \
	gst-plugin-id3demux gst-plugin-mad gst-plugin-ogg gst-plugin-playbin \
	gst-plugin-typefindfunctions gst-plugin-ivorbis gst-plugin-audioconvert \
	gst-plugin-wavparse python-netclient gst-plugin-mpegstream gst-plugin-selector \
	gst-plugin-flac gst-plugin-dvbmediasink gst-plugin-fluendo-mpegdemux \
	gst-plugin-neonhttpsrc gst-plugin-mpegaudioparse gst-plugin-subparse \
	eglibc-gconv-iso8859-15 \
	${@base_contains("MACHINE_FEATURES", "alsa", \
		"gst-plugin-alsa alsa-conf", "gst-plugin-ossaudio", d)}"

RDEPENDS_append_dm8000 = " gst-plugin-avi gst-plugin-matroska gst-plugin-qtdemux \
	gst-plugin-cdxaparse gst-plugin-cdxaparse gst-plugin-cdio gst-plugin-vcdsrc"
RDEPENDS_append_dm800 = " gst-plugin-matroska gst-plugin-qtdemux"

DESCRIPTION_append_enigma2-plugin-extensions-cutlisteditor = "enables you to cut your movies."
RDEPENDS_append_enigma2-plugin-extensions-cutlisteditor = " aio-grab"
DESCRIPTION_append_enigma2-plugin-extensions-graphmultiepg = "shows a graphical timeline EPG."
DESCRIPTION_append_enigma2-plugin-extensions-pictureplayer = "displays photos on the TV."
DESCRIPTION_append_enigma2-plugin-systemplugins-frontprocessorupdate = "keeps your frontprocessor up to date."
DESCRIPTION_append_enigma2-plugin-systemplugins-positionersetup = "helps you installing a motorized dish."
DESCRIPTION_append_enigma2-plugin-systemplugins-satelliteequipmentcontrol = "allows you to fine-tune DiSEqC-settings."
DESCRIPTION_append_enigma2-plugin-systemplugins-satfinder = "helps you to align your dish."
DESCRIPTION_append_enigma2-plugin-systemplugins-skinselector = "shows a menu with selectable skins."
DESCRIPTION_append_enigma2-plugin-systemplugins-videomode = "selects advanced video modes"
RDEPENDS_append_enigma2-plugin-extensions-dvdplayer = " libdreamdvd0"
RDEPENDS_append_enigma2-plugin-extensions-dvdburn = " cdrkit dvdauthor dvd+rw-tools mjpegtools projectx python-imaging"
RDEPENDS_append_enigma2-plugin-systemplugins-nfiflash = " python-twisted-web"
RDEPENDS_append_enigma2-plugin-systemplugins-softwaremanager = " python-pickle"

export LD="${CXX}"

PN = "enigma2"
PR = "r0"
SRCDATE = "20090214"
SRCREV = "935c5f9abeb0d6189c57f8406855d8437db8b0db"

# if you want experimental, use:
REL_MAJOR="2"
REL_MINOR="6"
BRANCH = "master"

# if you want a 2.5-based release, use
#REL_MAJOR="2"
#REL_MINOR="5"
#BRANCH = "enigma2_rel${REL_MAJOR}${REL_MINOR}"

PV = "${REL_MAJOR}.${REL_MINOR}git${SRCDATE}"

SRC_URI = "git://git.opendreambox.org/git/enigma2.git;protocol=git;branch=${BRANCH} \
	file://enigma2.sh \
	file://tuxtxt_caching.patch;patch=1;pnum=1"

SRC_URI_append_dm7025 = " file://enigma2-disable-iframesearch.patch;patch=1;pnum=1 \
	file://enigma2-disable-hardware-mp3-decode.patch;patch=1;pnum=1"

S = "${WORKDIR}/git"

FILES_${PN} += "${datadir}/fonts"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools pkgconfig

bindir = "/usr/bin"
sbindir = "/usr/sbin"

EXTRA_OECONF = "--enable-maintainer-mode --with-target=native --with-libsdl=no"

do_install_append() {
	install -m 0755 ${WORKDIR}/enigma2.sh ${D}/usr/bin/
}

python populate_packages_prepend () {
	enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)

	do_split_packages(d, enigma2_plugindir, '(.*?/.*?)/.*', 'enigma2-plugin-%s', '%s ', recursive=True, match_path=True, prepend=True)
}

do_stage_append() {
	install -d ${STAGING_INCDIR}/enigma2
	install -m 0644 ${S}/include/*.h ${STAGING_INCDIR}/enigma2
	for dir in actions base components driver dvb dvb/lowlevel dvb_ci gdi gui mmi nav python service; do
		install -d ${STAGING_INCDIR}/enigma2/lib/$dir;
		install -m 0644 ${S}/lib/$dir/*.h ${STAGING_INCDIR}/enigma2/lib/$dir;
	done
}
