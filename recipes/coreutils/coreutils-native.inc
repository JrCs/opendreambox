inherit native

INHIBIT_AUTOTOOLS_DEPS = "1"
PATCHTOOL = "patch"
LDFLAGS += "-lsepol -lselinux"

do_configure() {
	oe_runconf
}

do_install() {
	autotools_do_install
}

