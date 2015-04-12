# Copyright (C) 2015 Yves Blusseau <90z7oey02@sneakemail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OE-A Branding Lib for OE-A 2.0"
MAINTAINER = "oe-alliance team"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "python"

inherit gitpkgv autotools python-dir


SRCREV = "${AUTOREV}"
PV = "0.2+git${SRCPV}"
PKGV = "0.2+git${GITPKGV}"
PR = "r1"

SRC_URI="git://github.com/oe-alliance/branding-module.git;protocol=git"

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    --with-distro=${DISTRO_NAME} \
    --with-boxtype=${MACHINE} \
    --with-machineoem="${MACHINE_OEM}" \
    --with-machinebrand="${MACHINE_BRAND}" \
    --with-machinename="${MACHINE_NAME}" \
    --with-imageversion=${DISTRO_VERSION} \
    --with-imagebuild=${BUILD_VERSION} \
    --with-driverdate=${DRIVERSDATE} \
"

do_configure_prepend() {
    if [ "${MACHINE}" = "dm8000" ]; then
        MACHINE_OEM='dreambox'
        MACHINE_BRAND='Dreambox'
        MACHINE_NAME='DM8000HD'
        DRIVERSDATE='20131228'
    fi
}

FILES_${PN} = "/usr/lib/enigma2/python/*.so"
FILES_${PN}-dev += "/usr/lib/enigma2/python/*.la"
FILES_${PN}-staticdev += "/usr/lib/enigma2/python/*.a"
FILES_${PN}-dbg += "/usr/lib/enigma2/python/.debug"
