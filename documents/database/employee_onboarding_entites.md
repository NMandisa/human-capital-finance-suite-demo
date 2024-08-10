# Employee Onboarding Entities:

### 1. Employee
    - Employee ID (primary key)
    - Name
    - Date of Birth
    - Email
    - Phone Number
    - Address
### 2. Role
    - Role ID (primary key)
    - Role Name
    - Role Description
### 3. Responsibility
    - Responsibility ID (primary key)
    - Responsibility Name
    - Responsibility Description
### 4. Payroll
    - Payroll ID (primary key)
    - Employee ID (foreign key)
    - Payroll Date
    - Payroll Amount
### 5. Benefits
    - Benefits ID (primary key)
    - Employee ID (foreign key)
    - Benefits Type
    - Benefits Provider
### 6. Performance Management
    - Performance Management ID (primary key)
    - Employee ID (foreign key)
    - Performance Review Date
    - Performance Rating
### 7. Onboarding Materials
    - Onboarding Materials ID (primary key)
    - Employee ID (foreign key)
    - Material Type
    - Material Description

Relationship Diagrams:

1. Employee-Role
    - An employee can have multiple roles (one-to-many)
    - A role can be assigned to multiple employees (many-to-many)
    - Employee ID (FK) -> Role ID (PK)
2. Employee-Responsibility
    - An employee can have multiple responsibilities (one-to-many)
    - A responsibility can be assigned to multiple employees (many-to-many)
    - Employee ID (FK) -> Responsibility ID (PK)
3. Employee-Payroll
    - An employee can have multiple payroll records (one-to-many)
    - Employee ID (FK) -> Payroll ID (PK)
4. Employee-Benefits
    - An employee can have multiple benefits records (one-to-many)
    - Employee ID (FK) -> Benefits ID (PK)
5. Employee-Performance Management
    - An employee can have multiple performance management records (one-to-many)
    - Employee ID (FK) -> Performance Management ID (PK)
6. Employee-Onboarding Materials
    - An employee can have multiple onboarding materials records (one-to-many)
    - Employee ID (FK) -> Onboarding Materials ID (PK)

Note:

- PK = Primary Key
- FK = Foreign Key
- One-to-many (1:N) relationships are represented by a single arrowhead
- Many-to-many (M:N) relationships are represented by a double arrowhead