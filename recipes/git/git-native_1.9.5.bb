require git.inc
inherit native
DEPENDS = "openssl-native curl-native zlib-native expat-native"
PR = "r4"

SRC_URI[tarball.md5sum] = "e9c82e71bec550e856cccd9548902885"
SRC_URI[tarball.sha256sum] = "44efbc76edb50103e0d1b549dac006ee6f275fbd62491d7473e21127601f55c1"
SRC_URI[manpages.md5sum] = "87b8e8d3c5c5394c5d3af40d358353f3"
SRC_URI[manpages.sha256sum] = "37cc735c4ced6574e8e93349d52d30372fc6f9fe3de3d6b9934a14857d6aade8"

EXTRA_OECONF += "ac_cv_snprintf_returns_bogus=no ac_cv_c_c99_format=yes \
                 ac_cv_fread_reads_directories=${ac_cv_fread_reads_directories=yes} \
                 "
EXTRA_OEMAKE += "NO_GETTEXT=1"
