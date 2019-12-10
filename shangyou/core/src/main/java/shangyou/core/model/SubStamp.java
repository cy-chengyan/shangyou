package shangyou.core.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubStamp {

    private String sstid;

    private String stid;

    private int order;

    private String title;

    private String picture;

    private String faceValue;

    private String issuedNumber;

}
