DESCRIPTION = "Plugin for gstreamer: dvbmediasink"
SECTION = "multimedia"
PRIORITY = "optional"
MAINTAINER = "Felix Domke <tmbinc@openembedded.org>"
DEPENDS = "gstreamer"
PV = "0.10+cvs${SRCDATE}"
PR = "r0"
PR_dm7025 = "r1"

inherit autotools pkgconfig

SRCDATE = "20081128"
SRCDATE_dm7025 = "20080907"

SRC_URI = "cvs://anonymous@cvs.schwerkraft.elitedvb.net/cvsroot/dvbmediasink;module=dvbmediasink;method=pserver"

S = "${WORKDIR}/dvbmediasink"

FILES_${PN} = "${libdir}/gstreamer-0.10/*.so*"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_stage() {
	autotools_stage_all
}
