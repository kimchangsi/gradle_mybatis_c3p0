select user(), database();

select title_code,title_name from title;
select * from department;
select * from employee;

select dept_code, dept_name, dept_floor from department; 
select dept_code, dept_name, dept_floor from department

select eno, ename, salary, dept_code, dept_name, dept_floor, gender, joindate, title_code, title_name 
from employee e join department d on e.dno = d.dept_code
     join title t on e.title = t.title_code;
     
    select eno, ename, salary, dept_code, dept_name, dept_floor, gender, joindate, title_code, title_name 
		from employee e join department d on e.dno = d.dept_code
   		  join title t on e.title = t.title_code;
   		 

 
call salary_total(2);