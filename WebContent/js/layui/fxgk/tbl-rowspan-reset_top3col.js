//zhangcl 2018.12.16
$(function(){
    var b = null, l1 = '', c = 1;
    var b2 = null, l2 = '', c2 = 1;
    var b3 = null, l3 = '', c3 = 1;
    $("tbody tr").each(function() {
        var td = $(this).children("td").eq(0);
        var td2 = $(this).children("td").eq(1);
        var td3 = $(this).children("td").eq(2);
        var l1_ = td.text();
        var l2_ = td2.text();
        var l3_ = td3.text();
        //与上一层一样
        if(l1 == l1_) {
            td.remove();
            c = c + 1;

            if(l2 == l2_) {
                td2.remove();
                c2 = c2 + 1;

                if(l3 == l3_) {
                    td3.remove();
                    c3 = c3 + 1;
                } else {
                    l3 = l3_;
                    if(b3 != null) {
                        b3.attr("rowspan", c3);
                        c3 = 1;
                    }
                    b3 = td3;
                }
            } else {
                l2 = l2_;
                if(b2 != null) {
                    b2.attr("rowspan", c2);
                    c2 = 1;
                }
                b2 = td2;

                l3 = l3_;
                if(b3 != null) {
                    b3.attr("rowspan", c3);
                    c3 = 1;
                }
                b3 = td3;
            }

        } else {//与上一层不一样
            l1 = l1_;
            if(b != null) {
                b.attr("rowspan", c);
                c = 1;
            }
            b = td;

            l2 = l2_;
            if(b2 != null) {
                b2.attr("rowspan", c2);
                c2 = 1;
            }
            b2 = td2;
        }
    })
    if(b != null) {
        b.attr("rowspan", c);
    }
    if(b2 != null) {
        b2.attr("rowspan", c2);
    }
    if(b3 != null) {
        b3.attr("rowspan", c3);
    }
})