require clutter.inc

PV = "0.6.0+svnr${SRCPV}"
PR = "${INC_PR}.0"

SRC_URI = "svn://svn.o-hand.com/repos/clutter/branches;module=clutter-0-6;proto=http \
	   file://enable_tests-0.6.patch;patch=1 "

S = "${WORKDIR}/clutter-0-6"
