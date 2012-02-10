DESCRIPTION = "The git revision control system used by the Linux kernel developers"
SECTION = "console/utils"
LICENSE = "GPLv2"

SRC_URI = "http://git-core.googlecode.com/files/git-${PV}.tar.gz"
S = "${WORKDIR}/git-${PV}"

inherit autotools

do_install () {
	oe_runmake install DESTDIR="${D}" bindir=${bindir} \
		template_dir=${datadir}/git-core/templates \
		GIT_PYTHON_DIR=${datadir}/git-core/python
}

FILES_${PN} += "${datadir}/git-core"
