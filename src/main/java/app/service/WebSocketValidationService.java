package app.service;

import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.*;
import java.util.*;


/**
 * The type Web socket validation service.
 * @author s-wada
 */
@Service
public class WebSocketValidationService {

    /**
     * WebSocketの入力データに対し、バリデーションエラーのマップを返します。
     *
     * @param obj マッピングされたWebSocketの入力データ
     * @param <T> マッピングされた入力データの型パラメータ
     * @return バリデーションエラー。エラー無きときnullをかえします。
     */
    @Nullable
    public <T> Map<String, Object> validate(T obj) throws SecurityException{
        Set<ConstraintViolation<T>> constraintViolations;
        try{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            constraintViolations = validator.validate(obj);
        } catch (Exception e) {
            // 受信データが受けるデータ型に当てはまらない場合起こりうる。
            throw new SecurityException("不正なデータ送信が発生。", e);
        }

        if (constraintViolations.size() == 0) {
            return null;
        }

        Map errorMap = new HashMap<String, Object>();
        constraintViolations.stream().forEach(constraintViolation -> {
            String errorMsg = constraintViolation.getMessage();
            Path p = constraintViolation.getPropertyPath();
            List<String> errorPath = new LinkedList<String>();
            for (Path.Node node : p) {
                String name = node.getName();
                if (node.getIndex() != null) {
                    errorPath.add(node.getIndex().toString());
                }
                if (name != null) {
                    errorPath.add(name);
                }
            }
            insertError(errorMap, errorPath, errorMsg);
        });
        return errorMap;
    }

    /**
     * 元メッセージと同じ階層構造でバリデーションエラーのツリーを作成します。
     * @param errMap
     * @param errorPath
     * @param errorMsg
     */
    private void insertError(Map<String, Object> errMap, List<String> errorPath, String errorMsg) {
        Map subMap = errMap;
        int i;
        int pathSize = errorPath.size();
        for (i = 0; i < pathSize; i++) {
            String str = errorPath.get(i);
            if (!subMap.containsKey(str)) {
                if (i < pathSize - 1) {
                    Map map = new HashMap<String, Object>();
                    subMap.put(str, map);
                    subMap = map;
                }
                if (i + 1 == pathSize) {
                    subMap.put(str, errorMsg);
                }
            } else {
                subMap = (Map) subMap.get(str);
            }
        }
    }
}
