require gst-plugins.inc

PR = "r1"

#inherit gconf 

EXTRA_OECONF += "--with-check=no"
DEPENDS += "gst-plugins-base"

#PACKAGES =+ "gst-plugin-gconfelements"
#FILES_gst-plugin-gconfelements += "${sysconfdir}/gconf"
