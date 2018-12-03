# Setup examples environment

   Examples demonstrates Enterprise Integration Patterns (EIP) with Apache Camel in OSGi using Blueprint Dependency Injection Container. JUnit tests with CamelBlueprintTestSupport. 

## Installation

Download and install [Eclipse IDE](https://www.yatta.de/profiles/hub?1&packages&utm_source=eclipse&utm_medium=banner&utm_term=promoted+download&utm_campaign=Eclipse+Promoted+Download) for Java EE Developers.

Download [Karaf 4.1.6](http://karaf.apache.org/download.html)

## Prepare Eclipse IDE

- Install BndTools 4.0.0 plugin. Go to menu Help/EclipseMarketplace type BND in search field and press GO.
- Clone project in workplace and import it as existing project.

## Prepare Karaf OSGi container

```
# Add Camel feature repositories
```
```shell
karaf@root()> repo-add camel 2.21.1
karaf@root()> repo-add hawtio
karaf@root()> repo-add activemq 5.15.6
```
```
# Add Karaf features
```
```shell
karaf@root()> feature:install camel-core camel-blueprint aries-blueprint aries-blueprint shell-compat hawtio activemq-broker camel-jms
```
```
# Add BND remote agent
```
```shell
karaf@root(bundle)> install mvn:biz.aQute.bnd/biz.aQute.remote.agent/4.0.0
karaf@root(bundle)> start (aQute Bundle ID)
```
# Usage
Run projects from *.bndrun files as BND Native Launcher.