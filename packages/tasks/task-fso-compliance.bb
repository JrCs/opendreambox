DESCRIPTION = "The freesmartphone.org framework -- install this task to make your distribution FSO-compliant."
SECTION = "fso/base"
LICENSE = "MIT"
PV = "1.0"
FILE_PR = "r0"

inherit task

RDEPENDS_${PN} = "\
  frameworkd \
  fso-gpsd \
"

RRECOMMENDS_${PN} = "\
  gsm0710muxd \
"