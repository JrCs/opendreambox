require git.inc

SRC_URI += "file://git-less-hardlinks.diff;patch=1"

SRC_URI[src.md5sum] = "ab2716db51580037c7ebda4c8e9d56eb"
SRC_URI[src.sha256sum] = "d2a88d1564ebe468bb6a58a4edb57f5e06bda9846300cdbedbef8f7bccaf4ea6"
PR = "r1"

EXTRA_OECONF += "ac_cv_snprintf_returns_bogus=no ac_cv_c_c99_format=yes"
EXTRA_OECONF += "NO_TCLTK=yes"

EXTRA_OEMAKE += "V=1"

DEPENDS = "openssl curl zlib expat"
RDEPENDS_${PN} = "findutils sed"

# Dropbear ssh needs a wrapper script, so install openssh-ssh to make it work out of the box
RRECOMMENDS_${PN} = "openssh-ssh"

do_install_append() {
	# Fix broken hardlinks
	for gitprog in git-receive-pack git-upload-archive ; do
		rm ${D}${bindir}/$gitprog
		ln -sf ${bindir}/git ${D}${bindir}/$gitprog
	done
	for gitprog in git-cvsserver git-shell git-upload-pack ; do
		rm ${D}${libexecdir}/git-core/$gitprog
		ln -sf ${bindir}/$gitprog ${D}${libexecdir}/git-core/$gitprog
	done
	rm ${D}${libexecdir}/git-core/git && ln -sf ${bindir}/git ${D}${libexecdir}/git-core/git
	
	# Bash completion
	install -d ${D}${sysconfdir}/bash_completion.d/
	install -m 0755 contrib/completion/git-completion.bash ${D}${sysconfdir}/bash_completion.d/git
}

FILES_${PN}-dbg += "${libexecdir}/git-core/.debug"

PACKAGES =+ "${PN}-perltools"
FILES_${PN}-perltools += " \
	${libexecdir}/git-core/git-add--interactive \
	${libexecdir}/git-core/git-archimport \
	${libexecdir}/git-core/git-cvsexportcommit \
	${libexecdir}/git-core/git-cvsimport \
	${libexecdir}/git-core/git-cvsserver \
	${bindir}/git-cvsserver \
	${libexecdir}/git-core/git-difftool \
	${libexecdir}/git-core/git-relink \
	${libexecdir}/git-core/git-send-email \
	${libexecdir}/git-core/git-svn \
	${libdir}/perl5 \
"
RDEPENDS_${PN}-perltools = "${PN} perl perl-module-file-path findutils"

# # git-tk package with gitk and git-gui
# PACKAGES =+ "${PN}-tk"
# DEPENDS += "tcl-native"
# RDEPENDS_${PN}-tk = "${PN} tk tcl"
# EXTRA_OEMAKE = "TCL_PATH=${STAGING_BINDIR_CROSS}/tclsh"
# FILES_${PN}-tk = " \
# 	${bindir}/gitk \
# 	${datadir}/gitk \
# "
