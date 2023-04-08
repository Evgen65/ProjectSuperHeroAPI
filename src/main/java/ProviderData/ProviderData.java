package ProviderData;

import dto.ResponseDto;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProviderData {
    @DataProvider
    public Iterator<Object[]> responseDto() {
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{ResponseDto.builder()
                //  .id(0)
                .birthDate("2019-02-21")
                .city("Tel-Aviv")
                .fullName("Batman")
                .gender("M")
                .mainSkill("magic")
                .phone("+975225515178")
                .build()});
        return list.iterator();
    }
}
