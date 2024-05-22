# Database Migration Liquibase

### Core Concepts of Liquibase

## ChangeSet
### A changeSet is a set of changes that need to be applied to a database. Liquibase tracks the execution of changes at a ChangeSet level.

## Change
### A change describes a single change that needs to be applied to the database. Liquibase provides several change types like “create table” or “drop column” out of the box which are each an abstraction over a piece of SQL.

## Changelog
### The file which has the list of database changeSets that needs to be applied is called a changelog. These changelog files can be in either SQL, YAML, XML, or JSON format.

## Preconditions
### Preconditions are used to control the execution of changelogs or changeSets. They are used to define the state of the database under which the changeSets or changes logs need to be executed.

## Context
### A changeSet can be tagged with a context expression. Liquibase will evaluate this expression to determine if a changeSet should be executed at runtime, given a specific context. You could compare a context expression with environment variables.

## Labels
### The purpose of Labels is similar to that of contexts. The difference is that changeSets are tagged with a list of labels (not expressions), and during runtime, we can pass a label expression to choose the changeSets which match the expression.

## Changelog Parameters
### Liquibase allows us to have placeholders in changelogs, which it dynamically substitutes during runtime.

## Change Types
### Change type is operation
