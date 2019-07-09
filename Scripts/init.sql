-- ���̹�Ƽ
DROP SCHEMA IF EXISTS `mybatis_study`;

-- ���̹�Ƽ
CREATE SCHEMA `mybatis_study`;

-- ��å
CREATE TABLE `mybatis_study`.`title` (
	`title_code` INTEGER     NOT NULL COMMENT '��å��ȣ', -- ��å��ȣ
	`title_name` VARCHAR(20) NOT NULL COMMENT '��å��' -- ��å��
)
COMMENT '��å';

-- ��å
ALTER TABLE `mybatis_study`.`title`
	ADD CONSTRAINT `PK_title` -- ��å �⺻Ű
		PRIMARY KEY (
			`title_code` -- ��å��ȣ
		);

-- �μ�
CREATE TABLE `mybatis_study`.`department` (
	`dept_code`  INTEGER     NOT NULL COMMENT '�μ���ȣ', -- �μ���ȣ
	`dept_name`  VARCHAR(20) NOT NULL COMMENT '�μ���', -- �μ���
	`dept_floor` INTEGER     NULL     COMMENT '��ġ' -- ��ġ
)
COMMENT '�μ�';

-- �μ�
ALTER TABLE `mybatis_study`.`department`
	ADD CONSTRAINT `PK_department` -- �μ� �⺻Ű
		PRIMARY KEY (
			`dept_code` -- �μ���ȣ
		);

-- ���
CREATE TABLE `mybatis_study`.`employee` (
	`eno`      INTEGER     NOT NULL COMMENT '�����ȣ', -- �����ȣ
	`ename`    VARCHAR(20) NOT NULL COMMENT '�����', -- �����
	`salary`   INTEGER     NULL     COMMENT '�޿�', -- �޿�
	`gender`   TINYINT(1)  NULL     COMMENT '����', -- ����
	`joindate` DATE        NOT NULL COMMENT '�Ի�����', -- �Ի�����
	`dno`      INTEGER     NULL     COMMENT '�μ���ȣ', -- �μ���ȣ
	`title`    INTEGER     NULL     COMMENT '��å' -- ��å
)
COMMENT '���';

-- ���
ALTER TABLE `mybatis_study`.`employee`
	ADD CONSTRAINT `PK_employee` -- ��� �⺻Ű
		PRIMARY KEY (
			`eno` -- �����ȣ
		);

-- ���
ALTER TABLE `mybatis_study`.`employee`
	ADD CONSTRAINT `FK_department_TO_employee` -- �μ� -> ���
		FOREIGN KEY (
			`dno` -- �μ���ȣ
		)
		REFERENCES `mybatis_study`.`department` ( -- �μ�
			`dept_code` -- �μ���ȣ
		);

-- ���
ALTER TABLE `mybatis_study`.`employee`
	ADD CONSTRAINT `FK_title_TO_employee` -- ��å -> ���
		FOREIGN KEY (
			`title` -- ��å
		)
		REFERENCES `mybatis_study`.`title` ( -- ��å
			`title_code` -- ��å��ȣ
		);
		
	-- ������ ���Ѻο�
grant all privileges 
on mybatis_study.* 
to 'user_mybatis_study'@'localhost' 
identified by 'rootroot';