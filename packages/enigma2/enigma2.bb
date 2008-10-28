DESCRIPTION = "Enigma2 is an experimental, but useful framebuffer-based frontend for DVB functions"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
DEPENDS = "jpeg libungif libmad libpng libsigc++-1.2 gettext-native \
	dreambox-dvbincludes freetype libdvbsi++ python swig-native  \
	gstreamer gst-plugins-good gst-plugins-bad gst-plugins-ugly \
	libfribidi libxmlccwrap libdreamdvd tuxbox-tuxtxt-32bpp"
RDEPENDS = "python-codecs python-core python-lang python-re python-threading \
	python-xml python-fcntl python-elementtree gst-plugin-decodebin python-stringold \
	gst-plugin-id3demux gst-plugin-mad gst-plugin-ogg gst-plugin-playbin \
	gst-plugin-typefindfunctions gst-plugin-ivorbis gst-plugin-audioconvert \
	gst-plugin-wavparse python-netclient gst-plugin-mpegstream gst-plugin-selector \
	gst-plugin-flac"

RDEPENDS_append_dm7020 = " gst-plugin-ossaudio"
RDEPENDS_append_dm7025 = " gst-plugin-alsa alsa-conf"
RDEPENDS_append_dm8000 = " gst-plugin-alsa alsa-conf gst-plugin-avi gst-plugin-matroska \
	gst-plugin-subparse eglibc-gconv-iso8859-15 gst-plugin-cdxaparse"
RDEPENDS_append_dm800 = " gst-plugin-alsa alsa-conf"

DESCRIPTION_append_enigma2-plugin-extensions-cutlisteditor = "enables you to cut your movies."
DESCRIPTION_append_enigma2-plugin-extensions-graphmultiepg = "shows a graphical timeline EPG."
DESCRIPTION_append_enigma2-plugin-extensions-pictureplayer = "displays photos on the TV."
DESCRIPTION_append_enigma2-plugin-systemplugins-configurationbackup = "backs up your configuration and restores them optionally."
DESCRIPTION_append_enigma2-plugin-systemplugins-frontprocessorupdate = "keeps your frontprocessor up to date."
DESCRIPTION_append_enigma2-plugin-systemplugins-positionersetup = "helps you installing a motorized dish."
DESCRIPTION_append_enigma2-plugin-systemplugins-satelliteequipmentcontrol = "allows you to fine-tune DiSEqC-settings."
DESCRIPTION_append_enigma2-plugin-systemplugins-satfinder = "helps you to align your dish."
DESCRIPTION_append_enigma2-plugin-systemplugins-skinselector = "shows a menu with selectable skins."
DESCRIPTION_append_enigma2-plugin-systemplugins-videomode = "selects advanced video modes"
RDEPENDS_enigma2-plugin-extensions-dvdplayer = "libdreamdvd0"

export LD="${CXX}"

PN = "enigma2"
PR = "r0"
SRCDATE = "20081029"

# if you want experimental, use:
REL_MAJOR="2"
REL_MINOR="5"
TAG = ""

PV = "${REL_MAJOR}.${REL_MINOR}cvs${SRCDATE}"

SRC_URI = "cvs://anonymous@dreamboxupdate.com/cvs;module=enigma2;method=pserver${TAG};date=${SRCDATE} \
	file://tuxtxt_caching.patch;patch=1 \
	file://enigma2.sh"

SRC_URI_append_dm7025 = " file://enigma2-disable-iframesearch.patch;patch=1;pnum=1"

S = "${WORKDIR}/enigma2"

FILES_${PN} += "${datadir}/fonts"

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
	install -m 0644 ${WORKDIR}/enigma2/include/*.h ${STAGING_INCDIR}/enigma2
	for dir in actions base components driver dvb dvb/lowlevel dvb_ci gdi gui mmi nav python service; do
		install -d ${STAGING_INCDIR}/enigma2/lib/$dir;
		install -m 0644 ${WORKDIR}/enigma2/lib/$dir/*.h ${STAGING_INCDIR}/enigma2/lib/$dir;
	done
}
