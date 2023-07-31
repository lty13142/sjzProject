import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName PPAttrToSQLTest
 * @Description 省平台数据结果转换
 * @Author GZL
 * @Date 2023/2/6 15:50
 * @Version 1.0
 **/
public class PPAttrToSQLTest {

    @Test
    public void sptAttrToSql() throws Exception {
        String filePath = "C:\\Users\\Administrator\\Desktop\\sjk.txt";
        File jsonFile = new File(filePath);
        FileReader fileReader = new FileReader(jsonFile);
        Reader reader = new InputStreamReader(Files.newInputStream(jsonFile.toPath()), StandardCharsets.UTF_8);
        int ch;
        StringBuilder sb = new StringBuilder();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        String tableName = "pp_company_role";
        String tableComment = "固废企业信息_企业角色类型";
        String className = "PPCompanyInfo";
        // 生成建表语句
        generateCreateTableSQL(tableName, tableComment, sb);
        // 生成新增修改语句
//        generateInsertOrUpdateBatchSQL(tableName, tableComment, sb);
        // 表字段拆分
        tableFieldSplitting(sb);
        // 生成 javaBean
//        generateJavaBean(className, sb);
    }

    public void generateCreateTableSQL(String tableName, String tableComment, StringBuilder sb) {
        String[] lineList = sb.toString().split("\r\n");
        StringBuilder result = new StringBuilder("CREATE TABLE " + tableName + "  (\r\n");
        if (lineList.length > 0) {
            for (String lineData : lineList) {
                String[] columList = lineData.split("\t");
                result.append("\t").append(columList[3]).append("\t");
                switch (columList[2]){
                    case "STRING":
                        result.append(columList.length > 4 ? columList[4] : "varchar(100)").append(" COLLATE utf8mb4_bin ");
                        break;
                    case "FLOAT" :
                    case "DECIMAL":
                        result.append("decimal(19,4)");
                        break;
                    case "DATETIME":
                        result.append("datetime");
                        break;
                    case "DATE":
                        result.append("date");
                        break;
                    case "INT":
                        result.append("int");
                        break;
                    case "LONG":
                        result.append("bigint");
                        break;
                    case "SHORT":
                        result.append("smallint");
                        break;
                    default:
                        break;
                }
                result.append(Objects.equals("ID", columList[3]) ? " NOT NULL COMMENT '" : " DEFAULT NULL COMMENT '")
                        .append(columList[1]).append("',\r\n");
            }
        }
        result.append("PRIMARY KEY (`ID`) USING BTREE\r\n) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT = '")
                .append(tableComment)
                .append("'");
        System.out.println(result);
        System.out.println();
    }

    public void generateInsertOrUpdateBatchSQL(String tableName, String tableComment, StringBuilder sb) {
        StringBuilder sql = new StringBuilder("insert into " + tableName + "\n");
        String[] lineList = sb.toString().split("\r\n");
        // 3: 字段  4： 数据类型  1：注释
        if (lineList.length > 0) {
            StringBuilder insertAttrSql = new StringBuilder("(");
            StringBuilder insertValueSql = new StringBuilder("values\n" +
                    "<foreach collection=\"list\"  item=\"item\" index=\"index\" separator=\",\">\n\t(");
            StringBuilder updateSql = new StringBuilder("ON DUPLICATE KEY UPDATE\n");
            for (String lineData : lineList) {
                String[] columList = lineData.split("\t");
                insertAttrSql.append(Objects.equals("ID", columList[3]) ? columList[3] : "," + columList[3]);
                insertValueSql.append(Objects.equals("ID", columList[3]) ? "" : ",").append("#{item.").append(columList[3]).append("}");
                updateSql.append(Objects.equals("ID", columList[3]) ? "" : ",")
                        .append(columList[3]).append("=values(").append(columList[3]).append(")");
            }
            insertAttrSql.append(")\n");
            insertValueSql.append(")\n</foreach>\n");
            sql.append(insertAttrSql).append(insertValueSql).append(updateSql);
            System.out.println(sql);
        }
    }

    public void tableFieldSplitting(StringBuilder sb) {
        System.out.println("SQL 拆分====================>");
        String[] lineList = sb.toString().split("\r\n");
        // 3: 字段  4： 数据类型  1：注释
        List<Integer> list = Arrays.asList(1, 3, 4);
        if (lineList.length > 0) {
            for (Integer index : list) {
                for (String lineData : lineList) {
                    String[] columList = lineData.split("\t");
                    System.out.println(index > columList.length - 1
                            ? Objects.equals("FLOAT", columList[2]) || Objects.equals("DECIMAL", columList[2])
                            ? "decimal(19,4)" : columList[2].toLowerCase() : columList[index]);
                }
                System.out.println();
                System.out.println("====================>");
            }
        }
    }

    public void generateJavaBean(String className, StringBuilder sb) {
        System.out.println("JAVA====================>");
        StringBuilder result = new StringBuilder("import io.swagger.annotations.ApiModelProperty;\n" +
                "import lombok.Data;\n" +
                "\n" +
                "import java.io.Serializable;\n" +
                "import java.time.LocalDateTime;\n" +
                "import java.time.LocalDate;\n\n@Data\n" +
                "public class " + className + " implements Serializable {\n\n");
        result.append(" private static final long serialVersionUID = 1L;\n\n");
        String[] lineList = sb.toString().split("\r\n");
        // 3: 字段  4： 数据类型  1：注释
        if (lineList.length > 0) {
            for (String lineData : lineList) {
                String[] columList = lineData.split("\t");
                result.append("\t@ApiModelProperty(value = \"").append(columList[1]).append("\")\n");
                result.append("\tprivate ").append(Objects.equals("STRING", columList[2])
                        ? "String" : Objects.equals("FLOAT", columList[2]) || Objects.equals("DECIMAL", columList[2])
                        ? "BigDecimal" : Objects.equals("DATETIME", columList[2])
                        ? "LocalDateTime" : Objects.equals("DATE", columList[2])
                        ? "LocalDate" : Objects.equals("INTEGER", columList[2])
                        ? "Integer" : Objects.equals("LONG", columList[2])
                        ? "Long" : Objects.equals("SHORT", columList[2])
                        ? "Short" : Objects.equals("BOOLEAN", columList[2]) ? "Boolean" : "").append(" ").append(columList[3]).append(";\n\n");
            }
            result.append("\n}");
            System.out.println(result);
        }
    }
}
