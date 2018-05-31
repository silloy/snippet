<?php
include 'mysql_con.php';

$action = $_GET['action'];
$callback = $_GET['callback'];
if ($action == "query_disease"){
        $query = str_replace("'","",$_GET["query"]);
        $query = urldecode($query);
        //$query = $_GET["query"];
        $exact_match = false;
        if (substr(trim($query), -1, 1) == "@") {
        $query = str_replace('@', '', $query);
        $exact_match = true;
        }
        if (strlen(trim($query)) >0 && $exact_match == false){
            $select_disease = "select id, disease_name from disease where
            (disease_name like '%$query%' or pinyin like '%$query%' or pinyin_first like  '%$query%')  order by length(disease_name) asc limit 10";
        } elseif (strlen(trim($query)) > 0 && $exact_match == true) {
            $select_disease = "select id, disease_name from disease where
            (disease_name like '%$query%' or pinyin like '%$query%' or pinyin_first like  '%$query%')  order by length(disease_name) asc limit 10";
        } else {
            $select_disease = "select id, disease_name from disease where isevaluated = 'Y'";
        }
        $result = mysql_query($select_disease);
        $result_list = array();
        while($row = mysql_fetch_array($result)){
        $obj = new stdClass();
        $obj->id = $row["id"];
        $obj->disease_name = $row["disease_name"];
        array_push($result_list, $obj);
        }
        $result_obj = new stdClass();
        $result_obj->result = $result_list;
        if (isset($callback) && strlen(trim($callback)) > 0){
        echo $callback . '(' . json_encode($result_obj)  . ')';
        } else {
            echo json_encode($result_obj);
        }

    } elseif ($action == "disease_detail") {
        $query = $_GET["query"];

        $query_desc = "select definition , sex from disease where id=$query";
        $desc_result = mysql_query($query_desc);
        while($row = mysql_fetch_array($desc_result)){
            $disease_desc = $row['definition'];
            $disease_gender = $row['sex'];
            }

        //查找疾病症状
        $symptom_mapping = "select t1.id id, symptom_id s_id, symptom_relevance, symptom.type, t1.symptom_group `group`
        FROM
        (select disease_symptom_mapping.id id, symptom_id, symptom_relevance, symptom_group
        FROM disease_symptom_mapping WHERE disease_id= $query and ver=1)t1
        join symptom
        on t1.symptom_id=symptom.id where symptom.type=0 ORDER BY t1.symptom_relevance, id";
        $result = $mysqli->query($symptom_mapping);
        $symptom_result = array();

        while($row = $result->fetch_assoc()){
            $symptom = new stdClass();
            $row_id = $row['id'];
            $relevance = $row['symptom_relevance'];
            $symptom->id = $row['s_id'];
            $symptom->group = $row['group'];
            $symptom->rel = $relevance;
            $query_symptom_name = "select name from symptom where id=$symptom->id";
            $query_symptom_result = $mysqli->query($query_symptom_name);

            while($s_name_row = $query_symptom_result->fetch_assoc()){
                $symptom->name = $s_name_row[name];
                }

            $pv_detail = array();

            $list_entity = new stdClass();
            $list_entity->id = $row_id;
            $list_entity->symptom = $symptom;
            $list_entity->property = $pv_detail;

            array_push($symptom_result, $list_entity);

            }

        //查找体格检查
        $physical_mapping = "select t1.id id, symptom_id s_id, symptom.`name` s_name, symptom_relevance, symptom.type, t1.symptom_group `group`
        FROM
        (select disease_symptom_mapping.id id, symptom_id, symptom_relevance, symptom_group
        FROM disease_symptom_mapping WHERE disease_id= $query and ver=1)t1
        join symptom
        on t1.symptom_id=symptom.id where symptom.type=1 ORDER BY t1.symptom_relevance, id";
        $pe_result = mysql_query($physical_mapping);
        $physicalExam_result = array();


        while ($row = mysql_fetch_array($pe_result)) {

            $pe_detail = new stdClass();
            $row_id = $row['id'];
            $relevance = $row['symptom_relevance'];
            $pe_detail->id = $row['s_id'];

            $row_id = $row['id'];
            $pe_detail = new stdClass();
            $pe_detail->id = $row['s_id'];
            $pe_detail->group = $row['group'];
            $pe_detail->rel = $relevance;
            $pe_detail->name = $row['s_name'];

            $physical_entity = new stdClass();
            $physical_entity->id = $row_id;
            $physical_entity->physicalExam = $pe_detail;
            array_push($physicalExam_result, $physical_entity);
        }

        //查找疾病诱因
        $disCause_mapping = "select t1.id id, symptom_id s_id, symptom.`name` s_name, symptom_relevance, symptom.type, t1.symptom_group `group`
        FROM
        (select disease_symptom_mapping.id id, symptom_id, symptom_relevance, symptom_group
        FROM disease_symptom_mapping WHERE disease_id= $query and ver=1)t1
        join symptom
        on t1.symptom_id=symptom.id where symptom.type=2 ORDER BY t1.symptom_relevance, id";
        $dc_result = mysql_query($disCause_mapping);
        $diseaseCause_result = array();

        while ($row = mysql_fetch_array($dc_result)) {
            $dc_detail = new stdClass();
            $row_id = $row['id'];
            $relevance = $row['symptom_relevance'];
            $dc_detail->id = $row['s_id'];

            $row_id = $row['id'];
            $dc_detail = new stdClass();
            $dc_detail->id = $row['s_id'];
            $dc_detail->group = $row['group'];
            $dc_detail->rel = $relevance;
            $dc_detail->name = $row['s_name'];

            $diseaseCause_entity = new stdClass();
            $diseaseCause_entity->id = $row_id;
            $diseaseCause_entity->diseaseCause = $dc_detail;
            array_push($diseaseCause_result, $diseaseCause_entity);
        }

            $return_obj = new stdClass();
            $return_obj->disease_desc = $disease_desc;
            $return_obj->gender = $disease_gender;
            $return_obj->symptom_details = $symptom_result;
            $return_obj->physical_exams = $physicalExam_result;
            $return_obj->disease_cause = $diseaseCause_result;

            if (isset($callback) && strlen(trim($callback)) > 0){
                echo $callback.'('.json_encode($return_obj)  . ')';
                } else {
                    echo json_encode($return_obj);
                }

    } elseif ($action == "query_property"){
        $query = $_GET["query"];
        $query = urldecode($query);
        if (strlen(trim($query)) > 0){
            $select_property = "select id, name from property where name like '$query%' limit 10";
            } else {
            $select_property = "select id, name from property limit 10";
            }
        $result = mysql_query($select_property);
        $result_list = array();
        while($row = mysql_fetch_array($result)){
            $obj = new stdClass();
            $obj->id = $row["id"];
            $obj->name = $row["name"];
            array_push($result_list, $obj);
            }
        $return_obj = new stdClass();
        $return_obj->result = $result_list;
        if (isset($callback) && strlen(trim($callback)) > 0){
            echo $callback . '(' . json_encode($return_obj)  . ')';
            } else {
            echo json_encode($return_obj);
            }

    } elseif ($action == "query_property_value"){
        $query = $_GET["query"];
        $query = urldecode($query);
        $property_id = $_GET["property_id"];
        $factor_type = $_GET["factor_type"];
        if ($property_id == "2"){
            $table = "symptom";
            } else {
            $table = "property_value";
            }
        if (strlen(trim($query)) > 0){
            if ($property_id == "2"){
                $select_porperty_value = "select id, name from symptom where type = $factor_type and name like '%$query%' limit 10";
                } else {
                    $select_porperty_value = "select id, name from property_value where property_id = $property_id and name like '$query%' limit 10";
                    }
            } else {
                if ($property_id == "2"){
                    $select_porperty_value = "select id, name from symptom where type = $factor_type limit 10 ";
                    } else {
                        $select_porperty_value = "select id, name from property_value where property_id = $property_id limit 10";
                        }
                }
        $result = mysql_query($select_porperty_value);
        $result_list = array();
        while($row = mysql_fetch_array($result)){
            $obj = new stdClass();
            $obj->id = $row["id"];
            $obj->name = $row["name"];
            array_push($result_list, $obj);
            }
        $return_obj = new stdClass();
        $return_obj->result = $result_list;
        if (isset($callback) && strlen(trim($callback)) > 0){
            echo $callback . '(' . json_encode($return_obj)  . ')';
            } else {
                echo json_encode($return_obj);
            }

    } elseif ($action == "save_data"){
            $data = $_GET["data"];
            $debug = $_GET["debug"];
            $data_json = json_decode($data);
            $disease_id = $data_json->disease_id;
            $gender = $data_json->gender;
            $symptom_list = $data_json->symptom_list;
            $physicalExam_list = $data_json->physicalExam_list;
            $diseaseCause_list = $data_json->diseaseCause_list;

            $array = array();

            $sql_symptom = "select id from disease_symptom_mapping where disease_id=$disease_id";
            $symptom_result = mysql_query($sql_symptom);
            $record_old = array();
            $record_retain = array();
            while($row = mysql_fetch_array($symptom_result)){
                array_push($record_old, $row['id']);
                }

            //保存性别
            $sql_dis = "update disease set sex = $gender where id= $disease_id";
            mysql_query($sql_dis);
            array_push($array, $sql_dis);

            //保存症状
            $arrlength = count($symptom_list);
            for ($i = 0; $i < $arrlength; $i++){
                $record = $symptom_list[$i];
                $sym_Rel = $record->symtomRel;

                $synSymptom = $record->synSymptom;
                $min = PHP_INT_MAX;
                $arrlength_syn = count($synSymptom);

                $symptom_id = array();
                for ($j = 0; $j< $arrlength_syn; $j++){
                    $symptom_name = $synSymptom[$j]->symptom->name;
                    if (trim($symptom_name)==""){
                        continue;
                    }

                    $sql = "select id from symptom where `name` = '$symptom_name'";

                    $sql_result = mysql_query($sql);
                    $row = mysql_fetch_array($sql_result);

                    if(empty($row)){
                        $sql_n = "insert into symptom(`name`, type) values ('$symptom_name', 0)";
                        mysql_query($sql_n);
                        array_push($array, $sql_n);
                        $sql_query = "select id from symptom where `name` = '$symptom_name'";
                        array_push($array, $sql_query);
                        $sql_result_n = mysql_query($sql_query);
                        while ($row_n = mysql_fetch_array($sql_result_n)) {
                            $sym_id = $row_n['id'];
                            array_push($symptom_id, $sym_id);
                        }
                    } else{
                        $sym_id = $row['id'];
                        array_push($symptom_id, $sym_id);
                    }
                    if (isset($sym_id) && strlen(trim($sym_id)) > 0) {
                        if ($sym_id < $min){
                        $min = $sym_id;
                        }
                    }
                }
                for ($j = 0; $j < $arrlength_syn; $j++) {
                    $symptom_name = $synSymptom[$j]->symptom->name;
                    if (trim($symptom_name)==""){
                        continue;
                    }
                    $record_id = $synSymptom[$j]->id;
                    if (isset($record_id) && strlen(trim($record_id)) > 0){
                        $sql = "update disease_symptom_mapping set symptom_id = $symptom_id[$j],  symptom_relevance = $sym_Rel, symptom_group = $min where id = $record_id";
                        array_push($record_retain, $record_id);
                        } else {
                        $sql = "insert into disease_symptom_mapping (disease_id, symptom_id, symptom_relevance, symptom_group) values ($disease_id, $symptom_id[$j], $sym_Rel, $min)";
                        }
                    if (!isset($debug) || $debug == "false"){
                        mysql_query($sql);
                        }
                    array_push($array, $sql);
                }

            }


            //保存体格检查
            $arrlength = count($physicalExam_list);
            for ($i = 0; $i< $arrlength; $i++){

                $record = $physicalExam_list[$i];
                $physical_Rel = $record->physicalRel;

                $synPhysical = $record->synPhysical;
                $min = PHP_INT_MAX;
                $arrlength_syn = count($synPhysical);
                $break = false;

                $physical_id = array();
                for ($j = 0; $j< $arrlength_syn; $j++){
                    $physical_name = $synPhysical[$j]->physicalExam->name;
                    if (trim($physical_name)==""){
                        $break = true;
                        continue;
                    }

                    $sql = "select id from symptom where `name` = '$physical_name'";
                    $sql_result = mysql_query($sql);
                    $row = mysql_fetch_array($sql_result);

                    if(empty($row)){
                        $sql_n = "insert into symptom (`name`, type) values ('$physical_name', 1)";
                        mysql_query($sql_n);
                        array_push($array, $sql_n);
                        $sql_query = "select id from symptom where `name` = '$physical_name'";
                        array_push($array, $sql_query);
                        $sql_result_n = mysql_query($sql_query);
                        while ($row_n = mysql_fetch_array($sql_result_n)) {
                            $phy_id = $row_n['id'];
                            array_push($physical_id, $phy_id);
                        }
                    } else{
                        $phy_id = $row['id'];
                        array_push($physical_id, $phy_id);
                    }
                    if (isset($phy_id) && strlen(trim($phy_id)) > 0) {
                        if ($phy_id < $min){
                        $min = $phy_id;
                        }
                    }
                }
                for ($j = 0; $j < $arrlength_syn; $j++){
                    $physical_name = $synPhysical[$j]->physicalExam->name;
                    if (trim($physical_name)==""){
                        $break = true;
                        continue;
                    }
                    $record_id = $synPhysical[$j]->id;
                    if (isset($record_id) && strlen(trim($record_id)) > 0){
                        $sql = "update disease_symptom_mapping set symptom_id = $physical_id[$j],  symptom_relevance = $physical_Rel, symptom_group = $min where id = $record_id";
                        array_push($record_retain, $record_id);
                        } else {
                        $sql = "insert into disease_symptom_mapping (disease_id, symptom_id, symptom_relevance, symptom_group) values ($disease_id, $physical_id[$j], $physical_Rel, $min)";
                        }
                    if (!isset($debug) || $debug == "false"){
                        mysql_query($sql);
                        }
                    array_push($array, $sql);
                }
            }

            //保存诱因
            $arrlength = count($diseaseCause_list);
            for ($i = 0; $i< $arrlength; $i++){

                $record = $diseaseCause_list[$i];
                $cause_Rel = $record->causeRel;

                $synCause = $record->synCause;
                $min = PHP_INT_MAX;
                $arrlength_syn = count($synCause);
                $break = false;

                $cause_id = array();
                for ($j = 0; $j< $arrlength_syn; $j++){
                    $cause_name = $synCause[$j]->diseaseCause->name;
                    if (trim($cause_name)==""){
                        $break = true;
                        continue;
                    }

                    $sql = "select id from symptom where `name` = '$cause_name'";
                    $sql_result = mysql_query($sql);
                    $row = mysql_fetch_array($sql_result);

                    if(empty($row)){
                        $sql_n = "insert into symptom (`name`, type) values ('$cause_name', 2)";
                        mysql_query($sql_n);
                        array_push($array, $sql_n);
                        $sql_query = "select id from symptom where `name` = '$cause_name'";
                        array_push($array, $sql_query);
                        $sql_result_n = mysql_query($sql_query);
                        while ($row_n = mysql_fetch_array($sql_result_n)) {
                            $cau_id = $row_n['id'];
                            array_push($cause_id, $cau_id);
                        }
                    } else{
                        $cau_id = $row['id'];
                        array_push($cause_id, $cau_id);
                    }
                    if (isset($cau_id) && strlen(trim($cau_id)) > 0) {
                        if ($cau_id < $min){
                        $min = $cau_id;
                        }
                    }
                }
                for ($j = 0; $j < $arrlength_syn; $j++){
                    $cause_name = $synCause[$j]->diseaseCause->name;
                    if (trim($cause_name)==""){
                        $break = true;
                        continue;
                    }
                    $record_id = $synCause[$j]->id;
                    if (isset($record_id) && strlen(trim($record_id)) > 0){
                        $sql = "update disease_symptom_mapping set symptom_id = $cause_id[$j],  symptom_relevance = $cause_Rel, symptom_group = $min where id = $record_id";
                        array_push($record_retain, $record_id);
                        } else {
                        $sql = "insert into disease_symptom_mapping (disease_id, symptom_id, symptom_relevance, symptom_group) values ($disease_id, $cause_id[$j], $cause_Rel, $min)";
                        }
                    if (!isset($debug) || $debug == "false"){
                        mysql_query($sql);
                        }
                    array_push($array, $sql);
                }
            }

            //删除记录
            /*print_r($record_retain);*/
            $arrlength = count($record_old);
            for ($i = 0; $i< $arrlength; $i++){
                $record_id = $record_old[$i];
                if (!in_array($record_old[$i], $record_retain)){
                $sql_del = "delete from disease_symptom_mapping where disease_id = $disease_id and id=$record_id";
                array_push($array, $sql_del);
                mysql_query($sql_del);
                "sql_del";
                }
                }

            $return_obj = new stdClass();
            $return_obj->result = $array;
            if (isset($callback) && strlen(trim($callback)) > 0){
                echo $callback.'('.json_encode($return_obj).')';
                } else {
                echo json_encode($return_obj);
                }

    } elseif ($action == "add_value"){
            $property_id = $_GET["property_id"];
            $value = $_GET["value"];
            if ($property_id == "2"){
                $sql = "insert into symptom (name) values ('$value')";
                } elseif (isset($property_id)) {
                    $sql = "insert into property_value (name, property_id) values ('$value', $property_id)";
                    }
            mysql_query($sql);
                "$sql";
            $return_obj = new stdClass();
            $return_obj->result = "新增属性值成功!";
            if (isset($callback) && strlen(trim($callback)) > 0){
                echo $callback . '(' . json_encode($return_obj)  . ')';
                } else {
                    echo json_encode($return_obj);
                    }
            }
    mysql_close($con);

function formatProperty($property){
    $arrlength = count($property);
    $property_list = array();
    $value_list = array();
    for ($i = 0; $i< $arrlength; $i++){
        $p_id = $property[$i]-> property->id;
        if (!in_array($p_id, $property_list)){
            array_push($property_list, $p_id);
            }
        if (!array_key_exists($p_id, $value_list)){
            $value_list[$p_id] = array();
            }
        $v_id = $property[$i]->value->id;
        array_push($value_list[$p_id], $v_id);
        }
    $arrlength = count($property_list);
    $result = "";
    for ($i = 0; $i< $arrlength; $i++){
        $p_id = $property_list[$i];
        $v_id = "";
        $v_list = $value_list[$p_id];
        $v_length = count($v_list);
        for ($j = 0; $j < $v_length; $j++){
            if ($j > 0){
                $v_id = $v_id.",";
                }
            $v_id = $v_id.$v_list[$j];
            }
        if (strlen(trim($result)) > 0){
            $result = $result.";";
            }
        $result = $result.$p_id.":".$v_id;
        }
    return $result;
}
?>