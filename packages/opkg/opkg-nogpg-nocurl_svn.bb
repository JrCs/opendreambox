require opkg_svn.bb

DEPENDS = ""
PROVIDES += "opkg"

SRC_URI += "file://opkg_wget_nogpg_01_use_vfork_gunzip.patch;patch=1 \
	    file://opkg_wget_nogpg_02_use_vfork_system.patch;patch=1 \
	    file://opkg_wget_nogpg_03_fix_tmpdirs.patch;patch=1 \
	   "
PR = "r1"

SRCREV = "${SRCREV_pn-opkg}"

EXTRA_OECONF += "--disable-gpg --disable-curl --enable-static --disable-shared"

# The nogpg version isn't getting much love and has an unused variable which trips up -Werror
do_configure_prepend() {
	sed -i -e s:-Werror::g ${S}/libopkg/Makefile.am
}

DEFAULT_PREFERENCE = "-1"
