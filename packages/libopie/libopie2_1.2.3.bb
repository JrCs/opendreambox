require ${PN}.inc

PR = "r0"

SRC_URI = "${HANDHELDS_CVS};module=opie/libopie2 \
           file://include.pro"

SRC_URI_append_poodle = " file://poodle-2.6-hotkeys.patch;patch=1"