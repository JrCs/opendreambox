DESCRIPTION = " Speech Dispatcher is a high-level device independent layer \
for speech synthesis through a simple, stable and well documented interface."
HOMEPAGE = "http://www.freebsoft.org/speechd/"
LICENSE = "GPLv2"
DEPENDS = "flite dotconf glib-2.0"
RPROVIDES += "speechd"

PR = "r0"

inherit autotools

SRC_URI = "http://www.freebsoft.org/pub/projects/speechd/${PN}-${PV}.tar.gz \
           file://srcMakefile.am.patch;patch=1 "

LEAD_SONAME = "libspeechd.so"

do_install() {
        install -d ${D}${bindir}
        install -d ${D}${includedir}
        install -d ${D}${libdir}/${PN}-modules

        oe_libinstall -so -C src/audio libsdaudio ${D}${libdir}
        oe_libinstall -so -C src/c/api libspeechd ${D}${libdir}
        
        install -m 0644 ${S}/src/c/api/libspeechd.h    ${D}${includedir}
        install -m 0755 ${S}/src/c/clients/say/spd-say ${D}${bindir}
        install -m 0755 ${S}/src/c/clients/spdsend/spdsend ${D}${bindir}
        install -m 0755 ${S}/src/server/speech-dispatcher  ${D}${bindir}
        install -m 0755 ${S}/src/modules/sd_*   ${D}${libdir}/${PN}-modules/
}

do_stage() {
        install -m 0644 ${S}/src/c/api/libspeechd.h ${STAGING_INCDIR}
        oe_libinstall -so -C src/c/api libspeechd ${STAGING_LIBDIR}
}

FILES_${PN} += "${libdir}/${PN}-modules/*" 