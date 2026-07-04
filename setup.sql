create user userx with login password 'passx';
create database mydb;
grant all privileges on database mydb to userx;
\c mydb
grant all on schema public to userx;
