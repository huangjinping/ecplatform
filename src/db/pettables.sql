/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/8/22 10:47:33                           */
/*==============================================================*/

drop table if exists msg_member_message;

drop table if exists ps_member_pet;

drop table if exists ps_pet_camera;

drop table if exists msg_message;

drop table if exists ps_pet;

drop table if exists ps_camera;

drop table if exists mem_submember;

drop table if exists mem_member;

/*==============================================================*/
/* Table: mem_member                                            */
/*==============================================================*/
create table mem_member
(
   ID                   bigint not null auto_increment comment '主键',
   NAME                 varchar(128) comment '姓名',
   USER_NAME            varchar(64) comment '自定义用户登录名',
   MOBILE               varchar(20) comment '手机号',
   PHONE                varchar(20) comment '电话',
   QQ                   varchar(32) comment 'QQ',
   EMAIL                varchar(64) comment '邮箱',
   PASSWD               varchar(128) not null comment '密码',
   GENDER               bit comment '性别 0：男 1：女',
   ID_CARD              varchar(32) comment '身份证',
   BIRTHDAY             datetime comment '出生日期',
   NICK_NAME            varchar(32) comment '昵称',
   ENGLISH_NAME         varchar(32) comment '英文名',
   JOB                  varchar(128) comment '职业',
   ADDRESS              varchar(128) comment '住址',
   ORIGIN_PLACE         varchar(128) comment '籍贯',
   PHOTO                varchar(128) comment '照片文件系统地址',
   TENANT_ID            bigint comment '租户id',
   ORG_ID               bigint comment '部门id',
   BALANCE              decimal(12,2) comment '余额',
   ENABLE               bit default 1 comment '0:停用 1：可用',
   CREATE_TIME          datetime not null comment '创建时间',
   UPDATE_TIME          datetime not null comment '更新时间',
   CREATE_BY            bigint comment '创建人',
   UPDATE_BY            bigint comment '更新人',
   primary key (ID)
);

alter table mem_member comment '会员用户表';

/*==============================================================*/
/* Table: mem_submember                                         */
/*==============================================================*/
create table mem_submember
(
   ID                   bigint not null auto_increment comment '主键',
   NAME                 varchar(128) comment '姓名',
   USER_NAME            varchar(64) comment '自定义用户登录名',
   MOBILE               varchar(20) comment '手机号码',
   PASSWD               varchar(128) comment '密码',
   GENDER               bit comment '性别',
   P_MEM_ID             bigint comment '父账号会员ID',
   ENABLE               bit default 1 comment '0:停用 1：可用',
   CREATE_TIME          datetime not null comment '创建时间',
   UPDATE_TIME          datetime not null comment '更新时间',
   CREATE_BY            bigint comment '创建人',
   UPDATE_BY            bigint comment '更新人',
   primary key (ID)
);

/*==============================================================*/
/* Table: msg_member_message                                    */
/*==============================================================*/
create table msg_member_message
(
   ID                   bigint not null auto_increment comment '主键',
   MEM_ID               bigint comment '会员ID',
   MSG_ID               bigint comment '消息ID',
   primary key (ID)
);

alter table msg_member_message comment '会员消息关联表';

/*==============================================================*/
/* Table: msg_message                                           */
/*==============================================================*/
create table msg_message
(
   ID                   bigint not null auto_increment comment '主键',
   TITLE                varchar(128) comment '标题',
   MESSAGE              longtext comment '消息内容',
   TYPE                 varchar(10) comment '消息类型 0：公告消息',
   SEND_TIME            datetime comment '指定定时发送时间',
   CREATE_TIME          datetime not null comment '创建时间',
   UPDATE_TIME          datetime not null comment '更新时间',
   CREATE_BY            bigint comment '创建人',
   UPDATE_BY            bigint comment '更新人',
   primary key (ID)
);

alter table msg_message comment '消息记录表';

/*==============================================================*/
/* Table: ps_camera                                             */
/*==============================================================*/
create table ps_camera
(
   ID                   bigint not null auto_increment comment '主键',
   NAME                 varchar(32) comment '摄像头名称',
   IP                   varchar(64) not null comment 'IP地址',
   PORT                 int not null comment '端口号',
   AUTH_USERNAME        varchar(64) not null comment '验证用户名',
   AUTH_PASSWORD        varchar(64) not null comment '验证密码',
   DESCRIPTION          varchar(512) comment '描述信息',
   ENABLE               bit not null comment '0：不可用 1：可用',
   CAGE                 varchar(16) comment '对应寄养笼',
   BINDING              bit comment '是否绑定0：不绑定 1：绑定',
   CREATE_TIME          datetime not null comment '创建时间',
   UPDATE_TIME          datetime not null comment '更新时间',
   CREATE_BY            bigint comment '创建人',
   UPDATE_BY            bigint comment '更新人',
   primary key (ID)
);

alter table ps_camera comment '摄像头表';

/*==============================================================*/
/* Table: ps_member_pet                                         */
/*==============================================================*/
create table ps_member_pet
(
   ID                   bigint not null auto_increment comment '主键',
   MEM_ID               bigint not null comment '会员id',
   PET_ID               bigint not null comment '宠物id',
   primary key (ID)
);

alter table ps_member_pet comment '会员-宠物关系表';

/*==============================================================*/
/* Table: ps_pet                                                */
/*==============================================================*/
create table ps_pet
(
   ID                   bigint not null auto_increment comment '主键',
   NICK_NAME            varchar(32) not null comment '昵称',
   GENDER               bit comment '性别 0：公 1：母',
   BIRTHDAY             datetime comment '出生日期',
   DESCRIPTION          varchar(512) comment '描述信息',
   PHOTO                varchar(128) comment '照片文件系统路径',
   STATUS               varchar(10) comment '状态 0：未寄养 1：寄养中 2：已领走',
   CATEGORY             varchar(10) not null comment '大类别 0：狗 1：猫 2：其他',
   SMALL_CATEGORY       varchar(128) comment '小类别 哈巴狗 加菲猫',
   STARTFOSTER_TIME     datetime comment '开始寄养时间',
   ENDFOSTER_TIME       datetime comment '计划结束寄养时间',
   REALEND_TIME         datetime comment '实际结束寄养时间',
   CREATE_TIME          datetime not null comment '创建时间',
   UPDATE_TIME          datetime not null comment '更新时间',
   CREATE_BY            bigint comment '创建人',
   UPDATE_BY            bigint comment '更新人',
   primary key (ID)
);

alter table ps_pet comment '宠物表';

/*==============================================================*/
/* Table: ps_pet_camera                                         */
/*==============================================================*/
create table ps_pet_camera
(
   ID                   bigint not null auto_increment comment '主键',
   PET_ID               bigint not null comment '宠物id',
   CAM_ID               bigint not null comment '摄像头id',
   primary key (ID)
);

alter table ps_pet_camera comment '宠物-摄像头关系表';


alter table mem_submember add constraint FK_Reference_7 foreign key (P_MEM_ID)
      references mem_member (ID) on delete restrict on update restrict;

alter table msg_member_message add constraint FK_Reference_5 foreign key (MEM_ID)
      references mem_member (ID) on delete restrict on update restrict;

alter table msg_member_message add constraint FK_Reference_6 foreign key (MSG_ID)
      references msg_message (ID) on delete restrict on update restrict;

alter table ps_member_pet add constraint FK_Reference_1 foreign key (MEM_ID)
      references mem_member (ID) on delete restrict on update restrict;

alter table ps_member_pet add constraint FK_Reference_2 foreign key (PET_ID)
      references ps_pet (ID) on delete restrict on update restrict;

alter table ps_pet_camera add constraint FK_Reference_3 foreign key (PET_ID)
      references ps_pet (ID) on delete restrict on update restrict;

alter table ps_pet_camera add constraint FK_Reference_4 foreign key (CAM_ID)
      references ps_camera (ID) on delete restrict on update restrict;

