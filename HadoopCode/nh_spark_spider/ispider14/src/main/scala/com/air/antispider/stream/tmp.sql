select
       nh_process_info.id,nh_process_info.process_name,nh_strategy.crawler_blacklist_thresholds
from nh_process_info,nh_strategy where nh_process_info.id=nh_strategy.id and status=0


select
       *
from(
    select
           nh_rule.id,nh_rule.process_id,nh_rules_maintenance_table.rule_real_name,nh_rule.rule_type,nh_rule.crawler_type,nh_rule.status,nh_rule.arg0,nh_rule.arg1,nh_rule.score
    from nh_rule,nh_rules_maintenance_table
    where nh_rules_maintenance_table.rule_name=nh_rule.rule_name
    ) as tab where process_id = '"+process_id + "'and crawler_type="+n