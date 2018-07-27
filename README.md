# Readme

Originally written for Rundeck2/grails2, it has been updated for use with Rundeck3/grails3.

## What

This alternate oracle dialect is to tell the oracle driver to use updated (clob/blob/etc) 
instead of the deprecated long. This should allow rundeck to startup and create all tables without issues. 
Tested on Oracle12c.

## Install
copy jar file to:

`$RDECK_BASE/server/lib`

edit rundeck-config.properties change/add dialect to:

`dataSource.dialect = com.rundeck.hibernate.RundeckOracleDialect`

start up!

## Credit

original:

> All credit to @ikogan

repo originally created by jquick:

<https://github.com/jquick/rundeck_oracle_dialect>
