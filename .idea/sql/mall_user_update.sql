alter table `user`
add CONSTRAINT `user_chk_1` CHECK ((`sex` in (_utf8mb3'男',_utf8mb3'女'))),
add CONSTRAINT `user_chk_2` CHECK ((length(`telephone`) = 11)),
add CONSTRAINT `user_chk_3` CHECK ((`money` >= 0));
alter table `collection`
add constraint `type_check` check (`type` in ('commodity','brand'));