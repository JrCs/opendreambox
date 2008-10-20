DESCRIPTION = "A library for replaying C64 SID music"
SECTION = "libs"
PRIORITY = "optional"
LICENSE = "GPL"

FILE_PR = "r1"

SRC_URI = "http://critical.ch/distfiles/libsidplay-${PV}.tgz \
           http://ftp.debian.org/debian/pool/main/libs/libsidplay/libsidplay_1.36.59-5.diff.gz;patch=1 \
"

inherit autotools

do_stage() {
	autotools_stage_all
}
