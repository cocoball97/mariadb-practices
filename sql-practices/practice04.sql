-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 단 조회결과는 급여의 "내림차순"으로 정렬되어 나타나야 합니다. 

-- 문제1.
-- 현재 전체 사원의 평균 급여보다 많은 급여를 받는 사원은 몇 명이나 있습니까?
select count(*) as 사원수
from employees a
join salaries b on a.emp_no = b.emp_no
where b.to_date = '9999-01-01' and b.salary >(select avg(salary)
											 from salaries
                                             where to_date = '9999-01-01');

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 급여을 조회하세요. 단 조회결과는 급여의 내림차순으로 정렬합니다.
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, d.dept_name as 부서, b.salary as 급여
from employees a
join salaries b on a.emp_no = b.emp_no
join dept_emp c on a.emp_no = c.emp_no
join departments d on c.dept_no = d.dept_no
join (select c.dept_name, max(d.salary) as max_salary
from employees a
join dept_emp b on a.emp_no = b.emp_no
join departments c on b.dept_no = c.dept_no
join salaries d on a.emp_no = d.emp_no
where b.to_date = '9999-01-01' and d.to_date = '9999-01-01' 
group by c.dept_name) e on d.dept_name = e.dept_name and b.salary = e.max_salary
where b.to_date = '9999-01-01' and c.to_date = '9999-01-01' 
order by 급여 desc;

-- 문제3.
-- 현재, 자신의 부서 평균급여보다 급여가 많은 사원들의 사번, 이름 그리고 급여를 조회하세요 
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, b.salary as 급여
from employees a
join salaries b on a.emp_no = b.emp_no
join dept_emp c on a.emp_no = c.emp_no
join departments d on c.dept_no = d.dept_no
join (
    select a.dept_no, avg(b.salary) as avg_salary
    from dept_emp a
    join salaries b on a.emp_no = b.emp_no
    where a.to_date = '9999-01-01' and b.to_date = '9999-01-01'
    group by a.dept_no
) as e on c.dept_no = e.dept_no
where b.to_date = '9999-01-01' and c.to_date = '9999-01-01' and b.salary > e.avg_salary
order by 급여 desc;                   
                    
-- 문제4.
-- 현재, 사원들의 사번, 이름, 그리고 '매니저' 이름과 부서 이름을 출력해 보세요.
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, concat(a.first_name, ' ', a.last_name) as 매니저이름, d.dept_name
from employees a
join dept_emp b on a.emp_no = b.emp_no
join dept_manager c on b.dept_no = c.dept_no
join departments d on c.dept_no = d.dept_no
join (select *
	  from employees) e on a.emp_no = e.emp_no
where b.to_date = '9999-01-01' and c.to_date = '9999-01-01' ;


-- 문제5.
-- 현재, 평균급여가 가장 높은 부서의 사원들의 사번, 이름, 직책 그리고 급여를 조회하고 급여 순으로 출력하세요.  
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, d.title as 직책, b.salary as 급여
from employees a
join salaries b on a.emp_no = b.emp_no
join dept_emp c on a.emp_no = c.emp_no
join titles d on a.emp_no = d.emp_no
join (
    select a.dept_no, avg(b.salary) as avg_salary
    from dept_emp a
    join salaries b on a.emp_no = b.emp_no
    where a.to_date = '9999-01-01' and b.to_date = '9999-01-01'
    group by a.dept_no
    order by avg_salary desc
    limit 1
) as e on c.dept_no = e.dept_no
where b.to_date = '9999-01-01' and c.to_date = '9999-01-01' and d.to_date = '9999-01-01'
order by 급여 desc;   

-- 문제6.
-- 현재, 평균 급여가 가장 높은 부서의 이름 그리고 평균급여를 출력하세요.
select c.dept_name as 부서명, avg(b.salary) as 평균급여
from dept_emp a
join salaries b on a.emp_no = b.emp_no
join departments c on a.dept_no = c.dept_no 
where a.to_date = '9999-01-01' and b.to_date = '9999-01-01'
group by 부서명
order by 평균급여 desc
limit 1; 

-- 문제7.
-- 현재, 평균 급여가 가장 높은 직책의 타이틀 그리고 평균급여를 출력하세요.
select b.title as 직책, avg(a.salary) as 평균급여
from salaries a
join titles b on a.emp_no = b.emp_no
where a.to_date = '9999-01-01' and b.to_date = '9999-01-01'
group by 직책
order by 평균급여 desc
limit 1; 

