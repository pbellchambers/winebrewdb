#Changelog

##v2.0.0 (Not Released)

- Moved code to github
- Updated references in code from google to github
- Moved build script and dependency management to gradle
- Updated versions of dependencies
- Updated java minimum version
- Changed license to Apache v2.0
- Removed javadocs
- Tidied up project structure

##v1.3.2

- Fixed "potential resource leak" 

##v1.3.1

- Fixed bug when adding brew notes to brews that don't yet have a start date
- Code refactoring (no functional changes) 

##v1.3.0

- Made information tabs dynamic. You can add/edit your own tabs of data now (supports plain text or html).
- Added icon to add tabs
- Code refactoring on panel initialisation 

##v1.2.2

- Code refactoring (no functional changes)
- Slight rewording on calculator descriptions
- Changed some launch parameters on the native Mac version (still broken) 

##v1.2.1

- Added report a bug link under help menu
- Added check for updates function under help menu
- Builds automated with Ant now
- Native Mac .app available (may/may not work, currently untested) (doesn't work) 

##v1.2.0

- Printing Brews/Recipes/Ledger to .pdf now works
- Fixed divide by zero error on ledger when no brews/bottles in db
- Print button only displays when appropriate now
- Removed unneeded jars from project 

##v1.1.2

- Updated default sort ordering on various tables
- Removed "In Planning" brews from brew costs table 

##v1.1.1

- Ledger functionality complete
- Print buttons still do nothing, sorry! 

##v1.1.0

- First bit of ledger functionality
- Print buttons and ledger button still do nothing 

##v1.0.4

- Added new tables to database for future ledger functionality
- Added button to print brews/recipes (it currently does nothing and the icon is wrong too, best to ignore it for now) 

##v1.0.3

- Added Total Cost field to brews
- Minor text changes in about/dosages 

##v1.0.2

- Fixed new database recipe table issue 

##v1.0.1

- Fixed filepath issue on Mac/Linux 

##v1.0.0

- FIRST PROPER FULLY FUNCTIONAL WORKING RELEASE!!
- Font sizes updated on information panels
- Minor text changes
- Credits updated 

##v0.9.3

- Windows exe version available
- New database icon updated 

##v0.9.2

- Added .ini config with options for saving/editing db location and look and feel
- Fixed search/refresh issue after adding/editing
- Error handling for SQL Exceptions
- Refactored code
- Save picture button works
- Updated icons for measures calculator arrows
- Launch4j config added to source
- Alignment bugfixes
- Minor bugfixes 

##v0.8.1

- Look and feel changed and give choice between system/java
- Replaced buttons on left menu with icons
- Icons added/updated
- Updated fruit acids
- Top menu fully added and working
- Recipe search/add/edit done and working
- Updated readme 

##v0.7.1

- Set java compatibility to 1.6+
- Fixed non-Windows font issue
- Fixed issue with some brew pictures not displaying 

##vAlpha0.7.0

- Brew pictures panel added and working
- Formatting on number/date fields fixed
- Show all button added
- Minor bugs fixed 

##vAlpha0.6.2

- Brew notes panel added and working
- Added more to credits
- Disable all buttons when editing/adding
- Updated date fields to use date chooser
- Tidied up source files 

##vAlpha0.5.1

- Brew delete/cancel buttons added
- Now complies with third party license requirements
- Fixed bug with added brew data not in any status not being displayed
- Minor bugfixes 

##vAlpha0.5.0

- Add brew now works
- Brew data panel now populates and is editable
- Code refactoring
- Minor bugfixes 

##vAlpha0.3.0

- Brew search/filter now working
- Tabs added to brew pane
- Code refactoring
- Icon updated
- Pineapple added to fruit acids
- Minor bugfixes 

##vAlpha0.2.0

- Data can now be retrieved from the database 

##vAlpha0.1.1

- All calculator panels complete
- Added acknowledgements pop-up
- Text changes
- Icon added
- Window open position set 

##vAlpha0.0.2

- Alcohol & Dilution calculators done
- Information panels half done 

##vAlpha0.0.1

- First release, only basic layout present 
