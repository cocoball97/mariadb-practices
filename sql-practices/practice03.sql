-- 테이블 조인(JOIN) SQL 문제입니다.

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, b.salary as 급여
from employees a
join salaries b on a.emp_no = b.emp_no
where b.to_date = '9999-01-01'
order by b.salary desc;

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, b.title as 직책
from employees a
join titles b on a.emp_no = b.emp_no
where b.to_date = '9999-01-01'
order by 이름;

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요.
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, c.dept_name as 부서
from employees a
join dept_emp b on a.emp_no = b.emp_no
join departments c on b.dept_no = c.dept_no
where b.to_date = '9999-01-01'
order by 이름;

-- 문제4.
-- 현재 근무중인 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
-- 
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, b.salary as 연봉, c.title as 직책, e.dept_name as 부서
from employees a
join salaries b on a.emp_no = b.emp_no
join titles c on a.emp_no = c.emp_no
join dept_emp d on a.emp_no = d.emp_no 
join departments e on d.dept_no = e.dept_no
join (select a.emp_no, b.to_date
	  from employees a
	  join salaries b on a.emp_no = b.emp_no
	  where b.to_date = '9999-01-01') f on a.emp_no = f.emp_no and b.to_date = f.to_date 
where b.to_date = '9999-01-01' and c.to_date = '9999-01-01' and d.to_date = '9999-01-01' 
order by 이름;

-- 문제5.
-- 'Technique Leader'의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요.
-- (현재 'Technique Leader'의 직책으로 근무하는 사원은 고려하지 않습니다.)
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름
from employees a
join titles b on a.emp_no = b.emp_no
where b.title = 'Technique Leader' and (a.emp_no) not in(select a.emp_no
														from employees a
														join titles b on a.emp_no = b.emp_no
														where b.to_date = '9999-01-01' and b.title = 'Technique Leader');
                                                        
-- 문제6.
-- 직원 이름(last_name) 중에서 S로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select concat(a.first_name, ' ', a.last_name) as 이름, c.dept_name as 부서명, d.title as 직책
from employees a
join dept_emp b on a.emp_no = b.emp_no
join departments c on b.dept_no = c.dept_no
join titles d on a.emp_no = d.emp_no
where a.last_name like 'S%';

-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40,000 이상인 사원들의 사번, 이름, 급여 그리고 타이틀을 급여가 큰 순서대로 출력하세요.
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, b.salary as 급여, c.title as 직책
from employees a
join salaries b on a.emp_no = b.emp_no
join titles c on a.emp_no = c.emp_no
where b.to_date = '9999-01-01' and c.to_date = '9999-01-01' and c.title = 'Engineer' and b.salary > 40000
order by 급여 desc;

-- 문제8.
-- 현재, 평균급여가 50,000이 넘는 직책을 직책과 평균급여을 평균급여가 큰 순서대로 출력하세요.
select b.title as 직책, avg(a.salary) as 평균급여
from salaries a
join titles b on a.emp_no = b.emp_no
where a.to_date = '9999-01-01' and b.to_date = '9999-01-01'  
group by b.title
having 평균급여 > 50000
order by 평균급여 desc;

-- 문제9.
-- 현재, 부서별 평균급여를 평균급여가 큰 순서대로 부서명과 평균연봉을 출력 하세요.
select d.dept_name as 부서명, avg(b.salary) as 평균연봉
from employees a
join salaries b on a.emp_no = b.emp_no
join dept_emp c on a.emp_no = c.emp_no
join departments d on c.dept_no = d.dept_no
where b.to_date = '9999-01-01' and c.to_date = '9999-01-01'  
group by d.dept_name
order by 평균급여 desc;

-- 문제10.
-- 현재, 직책별 평균급여를 평균급여가 큰 직책 순서대로 직책명과 그 평균연봉을 출력 하세요.
select b.title as 직책, avg(a.salary) as 평균연봉
from salaries a
join titles b on a.emp_no = b.emp_no
where a.to_date = '9999-01-01' and b.to_date = '9999-01-01'  
group by b.title
order by 평균급여 desc;