DESCRIPTION = "Starling audio player for GPE"
SECTION = "gpe/multimedia"
PRIORITY = "optional"
LICENSE = "GPL"
DEPENDS = "gtk+ libgpewidget gstreamer gst-plugins-good gst-plugins-bad esound sqlite3 libsoup"

RDEPENDS = "esd \
        gst-plugin-audioconvert \
        gst-plugin-esd \
        gst-plugin-typefindfunctions \
        gst-plugin-decodebin \
        gst-plugin-volume"
RRECOMMENDS = "gst-plugin-mad \
        gst-plugin-modplug \
        gst-plugin-ivorbis \
        gst-plugin-tcp"

GPE_TARBALL_SUFFIX = "bz2"

inherit gpe autotools
