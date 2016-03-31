ALTER TABLE test ADD COLUMN tests_ORDER int(11) NOT NULL DEFAULT 0;

DROP procedure IF exists `add_test_order`;
DELIMITER $$
create procedure `add_test_order`()
  begin
    DECLARE i int default 0;
    DECLARE j int default 0;
    set @n = 0;
    set @i = 0;
    select count(*) from task into @n;
    while @i < @n do
      set i = @i;
      select id into @id from task limit i, 1;
      select count(*) into @testCount from test where task_id = @id;
      set @j = 0;
      while @j < @testCount do
        set j = @j;
        select id into @testId from test where task_id = @id limit j, 1;
        update test set tests_ORDER = j where id = @testId;
        set @j = @j +1;
      end while;
      set @i = @i +1;
    end while;
  end$$
DELIMITER ;

call add_test_order();

ALTER TABLE test DROP PRIMARY KEY, ADD PRIMARY KEY(id, tests_ORDER);