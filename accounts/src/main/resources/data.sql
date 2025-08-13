-- Customers
INSERT INTO customer (name, email, mobile_number, created_at, created_by, updated_at, updated_by)
VALUES 
('Ravi Kumar', 'ravi.kumar@example.com', '9876543210', CURRENT_DATE, 'admin', NULL, NULL),
('Priya Sharma', 'priya.sharma@example.com', '9123456780', CURRENT_DATE, 'admin', NULL, NULL),
('Arjun Reddy', 'arjun.reddy@example.com', '9988776655', CURRENT_DATE, 'admin', NULL, NULL);

-- Accounts
INSERT INTO accounts (customer_id, account_type, branch_address, created_at, created_by, updated_at, updated_by)
VALUES
(1, 'Savings', 'MG Road, Hyderabad', CURRENT_DATE, 'admin', NULL, NULL),
(1, 'Checking', 'MG Road, Hyderabad', CURRENT_DATE, 'admin', NULL, NULL),
(2, 'Savings', 'Sector 17, Chandigarh', CURRENT_DATE, 'admin', NULL, NULL),
(3, 'Current', 'Brigade Road, Bangalore', CURRENT_DATE, 'admin', NULL, NULL);
