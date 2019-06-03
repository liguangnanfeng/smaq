$(function(){
    var b = null, l1 = '', c = 1;
    var b2 = null, l2 = '', c2 = 1;
<<<<<<< HEAD
    var b3 = null, l3 = '', c3 = 1;
    var b4 = null, l4 = '', c4 = 1;
    $("tbody tr").each(function() {
        var td = $(this).children("td").eq(0);
        var td2 = $(this).children("td").eq(1);
        var td3 = $(this).children("td").eq(2);
        var td4 = $(this).children("td").eq(3);
        var l1_ = td.text();
        var l2_ = td2.text();
        var l3_ = td3.text();
        var l4_ = td4.text();

        //Same to top level
        if(l1 == l1_) {
            td.remove();
            c = c + 1;
            if(l2 == l2_) {
                td2.remove();
                c2 = c2 + 1;
				if(l3 == l3_) {
                    td3.remove();
                    c3 = c3 + 1;
					if(l4 == l4_) {
                       td4.remove();
                       c4 = c4 + 1;
				    }else {
                        l4 = l4_;
                        if(b4 != null) {
                           b4.attr("rowspan", c4);
                           c4 = 1;
                        }
                        b4 = td4;
					}
                } else {
                     l3 = l3_;
                     if(b3 != null) {
                        b3.attr("rowspan", c3);
                        c3 = 1;
                     }
                     b3 = td3;
                     l4 = l4_;
                     if(b4 != null) {
                        b4.attr("rowspan", c4);
                        c4 = 1;
                     }
                     b4 = td4;
				}
=======
    $("tbody tr").each(function() {
        var td = $(this).children("td").eq(0);
        var td2 = $(this).children("td").eq(1);
        var l1_ = td.text();
        var l2_ = td2.text();

        //与上一层一样
        if(l1 == l1_) {
            td.remove();
            c = c + 1;

            if(l2 == l2_) {
                td2.remove();
                c2 = c2 + 1;
>>>>>>> 62750a382b2898bf8dfbc00626a5a806f3be2008
            } else {
                l2 = l2_;
                if(b2 != null) {
                    b2.attr("rowspan", c2);
                    c2 = 1;
                }
                b2 = td2;
<<<<<<< HEAD
                l3 = l3_;
                if(b3 != null) {
                   b3.attr("rowspan", c3);
                   c3 = 1;
                   }
                b3 = td3;
                l4 = l4_;
                if(b4 != null) {
                   b4.attr("rowspan", c4);
                   c4 = 1;
                }
                b4 = td4;
            }

        } else {//Diffrent to top level
=======
            }

        } else {//与上一层不一样
>>>>>>> 62750a382b2898bf8dfbc00626a5a806f3be2008
            l1 = l1_;
            if(b != null) {
                b.attr("rowspan", c);
                c = 1;
            }
            b = td;
<<<<<<< HEAD
=======

>>>>>>> 62750a382b2898bf8dfbc00626a5a806f3be2008
            l2 = l2_;
            if(b2 != null) {
                b2.attr("rowspan", c2);
                c2 = 1;
            }
            b2 = td2;
<<<<<<< HEAD
            l3 = l3_;
            if(b3 != null) {
               b3.attr("rowspan", c3);
               c3 = 1;
            }
            b3 = td3;
            l4 = l4_;
            if(b4 != null) {
               b4.attr("rowspan", c4);
               c4 = 1;
            }
            b4 = td4;
=======
>>>>>>> 62750a382b2898bf8dfbc00626a5a806f3be2008
        }
    })
    if(b != null) {
        b.attr("rowspan", c);
    }
    if(b2 != null) {
        b2.attr("rowspan", c2);
    }
<<<<<<< HEAD
    if(b3 != null) {
        b3.attr("rowspan", c3);
    }
    if(b4 != null) {
        b4.attr("rowspan", c4);
    }
=======
>>>>>>> 62750a382b2898bf8dfbc00626a5a806f3be2008
})