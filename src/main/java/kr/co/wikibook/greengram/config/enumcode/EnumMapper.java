package kr.co.wikibook.greengram.config.enumcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 내부적로 관리하는 공통코드를 요청을 통해 확인하고 싶을 대 사용
public class EnumMapper {
    // key type: String, value type: List
    private Map<String, List<EnumMapperType>> factory = new HashMap<>();

    // e는 EnumMapperlValue를 상속받은 어떤 타입이든 전달 될 수 있다.
    public void put(String key, Class<? extends EnumMapperValue> e ){
        factory.put(key, toEnumValues(e));
    }

    private List<EnumMapperType> toEnumValues(Class<? extends EnumMapperValue> e){
        e.getEnumConstants(); // 특정 enum타입이 갖고 있는 모든 값을 출력시키는 기능은 class의 getEnumConstants
        return null;
    }
}
