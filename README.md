### FP Activity for the Week:

#### Deliverable
- **Deliverable**: Java command line application / Github Repo
- **Deadline**: Aug ~~30~~ 31, 2019 7pm
- **Attachment**: [sales.zip](sales)

##### Additional Information
- The ZIP file contains sales report from 3 store branches in _manila_, _cebu_, and _davao_ (_data is a peyk_)
- The reports are in CSV (comma-separated values) format
    - This file can be opened in _MS Excel_, _Numbers_, or _any text editor_.
- The data in the CSV is in this format: `ItemType,OrderDate,UnitsSold,UnitPrice`

#### Requirements
- Print all the items sold in alphabetical order (**_no explicit sorting_**)
- Print the total sales of each branches
- Print the total sales from all branches
- Print the total sales from all branches for the year **2016**
- Print what month the **Fruits** are sold the most
- Print the name of the most sold item in **2012** from all branches
- Print the month with the most number (units sold) of sales from all branches
- Print the name of the most item sold in **manila**
- Print the name of the most item sold in **cebu**
- Print the name of the most item sold in **davao**

#### Constraints
- All solutions must be solved using Java FP
- One application for all the requirements
- Non-interactive
    - The application will not request any input from the user. Just read the CSV
- Do not alter the given CSV files
- Do not create new files, just print the result in the screen using `System.out`
- _"Print the month"_ means print the english name of the month (i.e. `August` instead of `08`)
- _"...from all branches"_ means data from all CSV files will be combined
- _"Print the total sales of each branches"_ is expected to **output 3 numbers**
- printing string results (month/item name) is _cAsE-iNseNsiTiVe_

#### Hints
- `Files.lines(Path)`
- `Files.walk(Path)`
- `String.split(String)`
- `TreeSet`
- `String.equals(String)`
- `Stream.map(Function)`
- You are free to create any object that you see fit