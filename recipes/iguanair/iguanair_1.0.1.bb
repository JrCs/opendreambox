DESCRIPTION = "iguanaIR"
SECTION = "console/network"
PRIORITY = "optional"
HOMEPAGE = "http://iguanaworks.net"
LICENSE = "GPL"
DEPENDS = "popt"
DEPENDS += "virtual/libusb0"
RDEPENDS = ""

SRC_URI = http://iguanaworks.net/downloads/iguanaIR-${PV}.tar.bz2

S = "${WORKDIR}/iguanaIR-${PV}"

inherit autotools module-base
# inherit autotools module-base update-rc.d

#INITSCRIPT_PACKAGES = "lirc lirc-exec"
#INITSCRIPT_NAME = "lircd"
#INITSCRIPT_PARAMS = "defaults 20"
#INITSCRIPT_NAME_lirc-exec = "lircexec"
#INITSCRIPT_PARAMS_lirc-exec = "defaults 21"

# require lirc-config.inc

EXTRA_OECONF = "--disable-python"
# EXTRA_OEMAKE = 'SUBDIRS="daemons tools"'

do_stage() {
    oe_libinstall -so -C tools liblirc_client ${STAGING_LIBDIR}
	install -d ${STAGING_INCDIR}/iguanaIR/
	# install -m 0644 tools/lirc_client.h ${STAGING_INCDIR}/lirc/
}

do_configure() {
  export ac_cv_func_malloc_0_nonnull=yes
  export ac_cv_func_realloc_0_nonnull=yes
  oe_runconf
}

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	#install ${WORKDIR}/lircd.init ${D}${sysconfdir}/init.d/lircd
	#install ${WORKDIR}/lircexec.init ${D}${sysconfdir}/init.d/lircexec
        #install -d ${D}${datadir}/lirc/
     #   cp -pPR ${S}/remotes ${D}${datadir}/lirc/
	#rm -rf ${D}/dev
}

#FILES_${PN}-dbg += "${bindir}/.debug ${sbindir}/.debug"
FILES_${PN} = "${bindir}"
#FILES_lirc-x = "${bindir}/irxevent ${bindir}/xmode2"
#FILES_lirc-exec = "${bindir}/irexec ${sysconfdir}/init.d/lircexec"
#FILES_lirc-remotes = "${datadir}/lirc/remotes"
