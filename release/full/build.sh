#!/bin/zsh -eu

version="${1:-}"
vappearances_version="${2:-}"

if [ -z "$version" -o -z "$vappearances_version" ]; then
  echo "usage: $0 <version> <vappearances_version>" >&2
  exit 1
fi

ant clean
ant \
	-DRELEASE=$version \
	-DVAPPEARANCES_VERSION=$vappearances_version \
	-Ddebug=true

mvn install:install-file \
	-Dfile=dist/VAquaRendering.jar \
	-DgroupId=org.violetlib \
	-DartifactId=jnr \
	-Dversion=$version \
	-Dpackaging=jar

(cd ../../src && jar cf ../release/full/jnr-$version-sources.jar .)
mv jnr-$version-sources.jar ~/.m2/repository/org/violetlib/jnr/$version/
