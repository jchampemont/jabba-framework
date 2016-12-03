Change Log
=================================================
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/)
and this project adheres to [Semantic Versioning](http://semver.org/).

[Unreleased]
-------------------------------------------------
No unreleased changes yet

[0.1.0] - 2016-11-28
-------------------------------------------------
### Added
- [jabba-persistence](jabba-persistence/) sub-project with:
    - [BaseEntity.java](jabba-persistence/src/main/java/com/jeanchampemont/jabba/persistence/BaseEntity.java) to be 
    implemented by JPA entities, with default equals and hashCode methods implementations (based on entity ID).

