package com.windry.contactbible;
//Search Format Get + "book name" + Verse

public class SheetNumList {
    public int beginIdx;
    public int endIdx;
    public SheetNumList(){

    }
    public void GetGenesisVerse(String sheetTitle){
        switch(sheetTitle){
            case "Genesis 1":
            case "창세기 1":
                beginIdx = 1;
                endIdx = 31;
                break;
            case "Genesis 2":
            case "창세기 2":
                beginIdx = 32;
                endIdx = 56;
                break;
            case "Genesis 3":
            case "창세기 3":
                beginIdx = 57;
                endIdx = 80;
                break;
            case "Genesis 4":
            case "창세기 4":
                beginIdx = 81;
                endIdx = 106;
                break;
            case "Genesis 5":
            case "창세기 5":
                beginIdx = 107;
                endIdx = 138;
                break;
            case "Genesis 6":
            case "창세기 6":
                beginIdx = 139;
                endIdx = 160;
                break;
            case "Genesis 7":
            case "창세기 7":
                beginIdx = 161;
                endIdx = 184;
                break;
            case "Genesis 8":
            case "창세기 8":
                beginIdx = 185;
                endIdx = 206;
                break;
            case "Genesis 9":
            case "창세기 9":
                beginIdx = 207;
                endIdx = 235;
                break;
            case "Genesis 10":
            case "창세기 10":
                beginIdx = 234;
                endIdx = 267;
                break;
            case "Genesis 11":
            case "창세기 11":
                beginIdx = 268;
                endIdx = 299;
                break;
            case "Genesis 12":
            case "창세기 12":
                beginIdx = 300;
                endIdx = 319;
                break;
            case "Genesis 13":
            case "창세기 13":
                beginIdx = 320;
                endIdx = 337;
                break;
            case "Genesis 14":
            case "창세기 14":
                beginIdx = 338;
                endIdx = 361;
                break;
            case "Genesis 15":
            case "창세기 15":
                beginIdx = 362;
                endIdx = 382;
                break;
            case "Genesis 16":
            case "창세기 16":
                beginIdx = 383;
                endIdx = 398;
                break;
            case "Genesis 17":
            case "창세기 17":
                beginIdx = 399;
                endIdx = 425;
                break;
            case "Genesis 18":
            case "창세기 18":
                beginIdx = 426;
                endIdx = 458;
                break;
            case "Genesis 19":
            case "창세기 19":
                beginIdx = 459;
                endIdx = 496;
                break;
            case "Genesis 20":
            case "창세기 20":
                beginIdx = 497;
                endIdx = 514;
                break;
            case "Genesis 21":
            case "창세기 21":
                beginIdx = 515;
                endIdx = 548;
                break;
            case "Genesis 22":
            case "창세기 22":
                beginIdx = 549;
                endIdx = 572;
                break;
            case "Genesis 23":
            case "창세기 23":
                beginIdx = 573;
                endIdx = 592;
                break;
            case "Genesis 24":
            case "창세기 24":
                beginIdx = 593;
                endIdx = 659;
                break;
            case "Genesis 25":
            case "창세기 25":
                beginIdx = 660;
                endIdx = 693;
                break;
            case "Genesis 26":
            case "창세기 26":
                beginIdx = 694;
                endIdx = 728;
                break;
            case "Genesis 27":
            case "창세기 27":
                beginIdx = 729;
                endIdx = 774;
                break;
            case "Genesis 28":
            case "창세기 28":
                beginIdx = 775;
                endIdx = 796;
                break;
            case "Genesis 29":
            case "창세기 29":
                beginIdx = 797;
                endIdx = 831;
                break;
            case "Genesis 30":
            case "창세기 30":
                beginIdx = 832;
                endIdx = 874;
                break;
            case "Genesis 31":
            case "창세기 31":
                beginIdx = 875;
                endIdx = 929;
                break;
            case "Genesis 32":
            case "창세기 32":
                beginIdx = 930;
                endIdx = 961;
                break;
            case "Genesis 33":
            case "창세기 33":
                beginIdx = 962;
                endIdx = 981;
                break;
            case "Genesis 34":
            case "창세기 34":
                beginIdx = 982;
                endIdx = 1012;
                break;
            case "Genesis 35":
            case "창세기 35":
                beginIdx = 1013;
                endIdx = 1041;
                break;
            case "Genesis 36":
            case "창세기 36":
                beginIdx = 1042;
                endIdx = 1084;
                break;
            case "Genesis 37":
            case "창세기 37":
                beginIdx = 1085;
                endIdx = 1120;
                break;
            case "Genesis 38":
            case "창세기 38":
                beginIdx = 1121;
                endIdx = 1150;
                break;
            case "Genesis 39":
            case "창세기 39":
                beginIdx = 1151;
                endIdx = 1173;
                break;
            case "Genesis 40":
            case "창세기 40":
                beginIdx = 1174;
                endIdx = 1196;
                break;
            case "Genesis 41":
            case "창세기 41":
                beginIdx = 1197;
                endIdx = 1253;
                break;
            case "Genesis 42":
            case "창세기 42":
                beginIdx = 1254;
                endIdx = 1291;
                break;
            case "Genesis 43":
            case "창세기 43":
                beginIdx = 1292;
                endIdx = 1325;
                break;
            case "Genesis 44":
            case "창세기 44":
                beginIdx = 1326;
                endIdx = 1359;
                break;
            case "Genesis 45":
            case "창세기 45":
                beginIdx = 1360;
                endIdx = 1387;
                break;
            case "Genesis 46":
            case "창세기 46":
                beginIdx = 1388;
                endIdx = 1421;
                break;
            case "Genesis 47":
            case "창세기 47":
                beginIdx = 1422;
                endIdx = 1452;
                break;
            case "Genesis 48":
            case "창세기 48":
                beginIdx = 1453;
                endIdx = 1474;
                break;
            case "Genesis 49":
            case "창세기 49":
                beginIdx = 1475;
                endIdx = 1507;
                break;
            case "Genesis 50":
            case "창세기 50":
                beginIdx = 1508;
                endIdx = 1533;
                break;
        }
    }
    public void GetExodusVerse(String sheetTitle){
        switch (sheetTitle){
            case "Exodus 1":
            case "출애굽기 1":
                beginIdx = 1534;
                endIdx = 1555;
                break;
            case "Exodus 2":
            case "출애굽기 2":
                beginIdx = 1556;
                endIdx = 1580;
                break;
            case "Exodus 3":
            case "출애굽기 3":
                beginIdx = 1581;
                endIdx = 1602;
                break;
            case "Exodus 4":
            case "출애굽기 4":
                beginIdx = 1603;
                endIdx = 1633;
                break;
            case "Exodus 5":
            case "출애굽기 5":
                beginIdx = 1634;
                endIdx = 1656;
                break;
            case "Exodus 6":
            case "출애굽기 6":
                beginIdx = 1657;
                endIdx = 1686;
                break;
            case "Exodus 7":
            case "출애굽기 7":
                beginIdx = 1687;
                endIdx = 1711;
                break;
            case "Exodus 8":
            case "출애굽기 8":
                beginIdx = 1712;
                endIdx = 1743;
                break;
            case "Exodus 9":
            case "출애굽기 9":
                beginIdx = 1744;
                endIdx = 1778;
                break;
            case "Exodus 10":
            case "출애굽기 10":
                beginIdx = 1779;
                endIdx = 1807;
                break;
            case "Exodus 11":
            case "출애굽기 11":
                beginIdx = 1808;
                endIdx = 1817;
                break;
            case "Exodus 12":
            case "출애굽기 12":
                beginIdx = 1818;
                endIdx = 1868;
                break;
            case "Exodus 13":
            case "출애굽기 13":
                beginIdx = 1869;
                endIdx = 1890;
                break;
            case "Exodus 14":
            case "출애굽기 14":
                beginIdx = 1891;
                endIdx = 1921;
                break;
            case "Exodus 15":
            case "출애굽기 15":
                beginIdx = 1922;
                endIdx = 1948;
                break;
            case "Exodus 16":
            case "출애굽기 16":
                beginIdx = 1949;
                endIdx = 1984;
                break;
            case "Exodus 17":
            case "출애굽기 17":
                beginIdx = 1985;
                endIdx = 2000;
                break;
            case "Exodus 18":
            case "출애굽기 18":
                beginIdx = 2001;
                endIdx = 2027;
                break;
            case "Exodus 19":
            case "출애굽기 19":
                beginIdx = 2028;
                endIdx = 2052;
                break;
            case "Exodus 20":
            case "출애굽기 20":
                beginIdx = 2053;
                endIdx = 2078;
                break;
            case "Exodus 21":
            case "출애굽기 21":
                beginIdx = 2079;
                endIdx = 2114;
                break;
            case "Exodus 22":
            case "출애굽기 22":
                beginIdx = 2115;
                endIdx = 2145;
                break;
            case "Exodus 23":
            case "출애굽기 23":
                beginIdx = 2146;
                endIdx = 2178;
                break;
            case "Exodus 24":
            case "출애굽기 24":
                beginIdx = 2179;
                endIdx = 2196;
                break;
            case "Exodus 25":
            case "출애굽기 25":
                beginIdx = 2197;
                endIdx = 2236;
                break;
            case "Exodus 26":
            case "출애굽기 26":
                beginIdx = 2237;
                endIdx = 2273;
                break;
            case "Exodus 27":
            case "출애굽기 27":
                beginIdx = 2274;
                endIdx = 2294;
                break;
            case "Exodus 28":
            case "출애굽기 28":
                beginIdx = 2295;
                endIdx = 2337;
                break;
            case "Exodus 29":
            case "출애굽기 29":
                beginIdx = 2338;
                endIdx = 2383;
                break;
            case "Exodus 30":
            case "출애굽기 30":
                beginIdx = 2384;
                endIdx = 2421;
                break;
            case "Exodus 31":
            case "출애굽기 31":
                beginIdx = 2422;
                endIdx = 2439;
                break;
            case "Exodus 32":
            case "출애굽기 32":
                beginIdx = 2440;
                endIdx = 2474;
                break;
            case "Exodus 33":
            case "출애굽기 33":
                beginIdx = 2475;
                endIdx = 2497;
                break;
            case "Exodus 34":
            case "출애굽기 34":
                beginIdx = 2498;
                endIdx = 2532;
                break;
            case "Exodus 35":
            case "출애굽기 35":
                beginIdx = 2533;
                endIdx = 2567;
                break;
            case "Exodus 36":
            case "출애굽기 36":
                beginIdx = 2568;
                endIdx = 2605;
                break;
            case "Exodus 37":
            case "출애굽기 37":
                beginIdx = 2606;
                endIdx = 2634;
                break;
            case "Exodus 38":
            case "출애굽기 38":
                beginIdx = 2635;
                endIdx = 2665;
                break;
            case "Exodus 39":
            case "출애굽기 39":
                beginIdx = 2666;
                endIdx = 2708;
                break;
            case "Exodus 40":
            case "출애굽기 40":
                beginIdx = 2709;
                endIdx = 2746;
                break;
        }
    }
    public void GetLeviticusVerse(String sheetTitle){
        switch (sheetTitle){
            case "Leviticus 1":
            case "레위기 1":
                beginIdx = 2747;
                endIdx = 2763;
                break;
            case "Leviticus 2":
            case "레위기 2":
                beginIdx = 2764;
                endIdx = 2779;
                break;
            case "Leviticus 3":
            case "레위기 3":
                beginIdx = 2780;
                endIdx = 2796;
                break;
            case "Leviticus 4":
            case "레위기 4":
                beginIdx = 2797;
                endIdx = 2831;
                break;
            case "Leviticus 5":
            case "레위기 5":
                beginIdx = 2832;
                endIdx = 2850;
                break;
            case "Leviticus 6":
            case "레위기 6":
                beginIdx = 2851;
                endIdx = 2880;
                break;
            case "Leviticus 7":
            case "레위기 7":
                beginIdx = 2881;
                endIdx = 2918;
                break;
            case "Leviticus 8":
            case "레위기 8":
                beginIdx = 2919;
                endIdx = 2954;
                break;
            case "Leviticus 9":
            case "레위기 9":
                beginIdx = 2955;
                endIdx = 2978;
                break;
            case "Leviticus 10":
            case "레위기 10":
                beginIdx = 2979;
                endIdx = 2998;
                break;
            case "Leviticus 11":
            case "레위기 11":
                beginIdx = 2999;
                endIdx = 3045;
                break;
            case "Leviticus 12":
            case "레위기 12":
                beginIdx = 3046;
                endIdx = 3053;
                break;
            case "Leviticus 13":
            case "레위기 13":
                beginIdx = 3054;
                endIdx = 3112;
                break;
            case "Leviticus 14":
            case "레위기 14":
                beginIdx = 3113;
                endIdx = 3169;
                break;
            case "Leviticus 15":
            case "레위기 15":
                beginIdx = 3170;
                endIdx = 3202;
                break;
            case "Leviticus 16":
            case "레위기 16":
                beginIdx = 3203;
                endIdx = 3236;
                break;
            case "Leviticus 17":
            case "레위기 17":
                beginIdx = 3237;
                endIdx = 3252;
                break;
            case "Leviticus 18":
            case "레위기 18":
                beginIdx = 3253;
                endIdx = 3282;
                break;
            case "Leviticus 19":
            case "레위기 19":
                beginIdx = 3283;
                endIdx = 3319;
                break;
            case "Leviticus 20":
            case "레위기 20":
                beginIdx = 3320;
                endIdx = 3346;
                break;
            case "Leviticus 21":
            case "레위기 21":
                beginIdx = 3347;
                endIdx = 3370;
                break;
            case "Leviticus 22":
            case "레위기 22":
                beginIdx = 3371;
                endIdx = 3403;
                break;
            case "Leviticus 23":
            case "레위기 23":
                beginIdx = 3404;
                endIdx = 3447;
                break;
            case "Leviticus 24":
            case "레위기 24":
                beginIdx = 3448;
                endIdx = 3470;
                break;
            case "Leviticus 25":
            case "레위기 25":
                beginIdx = 3471;
                endIdx = 3525;
                break;
            case "Leviticus 26":
            case "레위기 26":
                beginIdx = 3526;
                endIdx = 3571;
                break;
            case "Leviticus 27":
            case "레위기 27":
                beginIdx = 3572;
                endIdx = 3605;
                break;
        }
    }
    public void GetNumbersVerse(String sheetTitle){
        switch (sheetTitle){
            case "Numbers 1":
            case "민수기 1":
                beginIdx = 3606;
                endIdx = 3659;
                break;
            case "Numbers 2":
            case "민수기 2":
                beginIdx = 3660;
                endIdx = 3693;
                break;
            case "Numbers 3":
            case "민수기 3":
                beginIdx = 3694;
                endIdx = 3744;
                break;
            case "Numbers 4":
            case "민수기 4":
                beginIdx = 3745;
                endIdx = 3793;
                break;
            case "Numbers 5":
            case "민수기 5":
                beginIdx = 3794;
                endIdx = 3824;
                break;
            case "Numbers 6":
            case "민수기 6":
                beginIdx = 3825;
                endIdx = 3851;
                break;
            case "Numbers 7":
            case "민수기 7":
                beginIdx = 3852;
                endIdx = 3940;
                break;
            case "Numbers 8":
            case "민수기 8":
                beginIdx = 3941;
                endIdx = 3966;
                break;
            case "Numbers 9":
            case "민수기 9":
                beginIdx = 3967;
                endIdx = 3989;
                break;
            case "Numbers 10":
            case "민수기 10":
                beginIdx = 3990;
                endIdx = 4025;
                break;
            case "Numbers 11":
            case "민수기 11":
                beginIdx = 4026;
                endIdx = 4060;
                break;
            case "Numbers 12":
            case "민수기 12":
                beginIdx = 4061;
                endIdx = 4076;
                break;
            case "Numbers 13":
            case "민수기 13":
                beginIdx = 4077;
                endIdx = 4109;
                break;
            case "Numbers 14":
            case "민수기 14":
                beginIdx = 4110;
                endIdx = 4154;
                break;
            case "Numbers 15":
            case "민수기 15":
                beginIdx = 4155;
                endIdx = 4195;
                break;
            case "Numbers 16":
            case "민수기 16":
                beginIdx = 4196;
                endIdx = 4245;
                break;
            case "Numbers 17":
            case "민수기 17":
                beginIdx = 4246;
                endIdx = 4258;
                break;
            case "Numbers 18":
            case "민수기 18":
                beginIdx = 4259;
                endIdx = 4290;
                break;
            case "Numbers 19":
            case "민수기 19":
                beginIdx = 4291;
                endIdx = 4312;
                break;
            case "Numbers 20":
            case "민수기 20":
                beginIdx = 4313;
                endIdx = 4341;
                break;
            case "Numbers 21":
            case "민수기 21":
                beginIdx = 4342;
                endIdx = 4375;
                break;
            case "Numbers 22":
            case "민수기 22":
                beginIdx = 4376;
                endIdx = 4417;
                break;
            case "Numbers 23":
            case "민수기 23":
                beginIdx = 4418;
                endIdx = 4447;
                break;
            case "Numbers 24":
            case "민수기 24":
                beginIdx = 4448;
                endIdx = 4472;
                break;
            case "Numbers 25":
            case "민수기 25":
                beginIdx = 4473;
                endIdx = 4490;
                break;
            case "Numbers 26":
            case "민수기 26":
                beginIdx = 4491;
                endIdx = 4555;
                break;
            case "Numbers 27":
            case "민수기 27":
                beginIdx = 4556;
                endIdx = 4578;
                break;
            case "Numbers 28":
            case "민수기 28":
                beginIdx = 4579;
                endIdx = 4609;
                break;
            case "Numbers 29":
            case "민수기 29":
                beginIdx = 4610;
                endIdx = 4649;
                break;
            case "Numbers 30":
            case "민수기 30":
                beginIdx = 4650;
                endIdx = 4665;
                break;
            case "Numbers 31":
            case "민수기 31":
                beginIdx = 4666;
                endIdx = 4719;
                break;
            case "Numbers 32":
            case "민수기 32":
                beginIdx = 4720;
                endIdx = 4761;
                break;
            case "Numbers 33":
            case "민수기 33":
                beginIdx = 4762;
                endIdx = 4817;
                break;
            case "Numbers 34":
            case "민수기 34":
                beginIdx = 4818;
                endIdx = 4846;
                break;
            case "Numbers 35":
            case "민수기 35":
                beginIdx = 4847;
                endIdx = 4880;
                break;
            case "Numbers 36":
            case "민수기 36":
                beginIdx = 4881;
                endIdx = 4893;
                break;
        }
    }
    public void GetDeuteronomyVerse(String sheetTitle){
        switch (sheetTitle){
            case "Deuteronomy 1":
            case "신명기 1":
                beginIdx = 4894;
                endIdx = 4939;
                break;
            case "Deuteronomy 2":
            case "신명기 2":
                beginIdx = 4940;
                endIdx = 4976;
                break;
            case "Deuteronomy 3":
            case "신명기 3":
                beginIdx = 4977;
                endIdx = 5005;
                break;
            case "Deuteronomy 4":
            case "신명기 4":
                beginIdx = 5006;
                endIdx = 5054;
                break;
            case "Deuteronomy 5":
            case "신명기 5":
                beginIdx = 5055;
                endIdx = 5087;
                break;
            case "Deuteronomy 6":
            case "신명기 6":
                beginIdx = 5088;
                endIdx = 5112;
                break;
            case "Deuteronomy 7":
            case "신명기 7":
                beginIdx = 5113;
                endIdx = 5138;
                break;
            case "Deuteronomy 8":
            case "신명기 8":
                beginIdx = 5139;
                endIdx = 5158;
                break;
            case "Deuteronomy 9":
            case "신명기 9":
                beginIdx = 5159;
                endIdx = 5187;
                break;
            case "Deuteronomy 10":
            case "신명기 10":
                beginIdx = 5188;
                endIdx = 5209;
                break;
            case "Deuteronomy 11":
            case "신명기 11":
                beginIdx = 5210;
                endIdx = 5241;
                break;
            case "Deuteronomy 12":
            case "신명기 12":
                beginIdx = 5242;
                endIdx = 5273;
                break;
            case "Deuteronomy 13":
            case "신명기 13":
                beginIdx = 5274;
                endIdx = 5291;
                break;
            case "Deuteronomy 14":
            case "신명기 14":
                beginIdx = 5292;
                endIdx = 5320;
                break;
            case "Deuteronomy 15":
            case "신명기 15":
                beginIdx = 5321;
                endIdx = 5343;
                break;
            case "Deuteronomy 16":
            case "신명기 16":
                beginIdx =5344;
                endIdx = 5365;
                break;
            case "Deuteronomy 17":
            case "신명기 17":
                beginIdx = 5366;
                endIdx = 5385;
                break;
            case "Deuteronomy 18":
            case "신명기 18":
                beginIdx = 5386;
                endIdx = 5407;
                break;
            case "Deuteronomy 19":
            case "신명기 19":
                beginIdx = 5408;
                endIdx = 5428;
                break;
            case "Deuteronomy 20":
            case "신명기 20":
                beginIdx = 5429;
                endIdx = 5448;
                break;
            case "Deuteronomy 21":
            case "신명기 21":
                beginIdx = 5449;
                endIdx = 5471;
                break;
            case "Deuteronomy 22":
            case "신명기 22":
                beginIdx = 5472;
                endIdx = 5501;
                break;
            case "Deuteronomy 23":
            case "신명기 23":
                beginIdx = 5502;
                endIdx = 5526;
                break;
            case "Deuteronomy 24":
            case "신명기 24":
                beginIdx = 5527;
                endIdx = 5548;
                break;
            case "Deuteronomy 25":
            case "신명기 25":
                beginIdx = 5549;
                endIdx = 5567;
                break;
            case "Deuteronomy 26":
            case "신명기 26":
                beginIdx = 5568;
                endIdx = 5586;
                break;
            case "Deuteronomy 27":
            case "신명기 27":
                beginIdx = 5587;
                endIdx = 5612;
                break;
            case "Deuteronomy 28":
            case "신명기 28":
                beginIdx = 5613;
                endIdx = 5680;
                break;
            case "Deuteronomy 29":
            case "신명기 29":
                beginIdx = 5681;
                endIdx = 5709;
                break;
            case "Deuteronomy 30":
            case "신명기 30":
                beginIdx = 5710;
                endIdx = 5729;
                break;
            case "Deuteronomy 31":
            case "신명기 31":
                beginIdx = 5730;
                endIdx = 5759;
                break;
            case "Deuteronomy 32":
            case "신명기 32":
                beginIdx = 5760;
                endIdx = 5811;
                break;
            case "Deuteronomy 33":
            case "신명기 33":
                beginIdx = 5812;
                endIdx = 5840;
                break;
            case "Deuteronomy 34":
            case "신명기 34":
                beginIdx = 5841;
                endIdx = 5852;
                break;
        }
    }
    public void GetJoshuaVerse(String sheetTitle){
        switch (sheetTitle){
            case "Joshua 1":
            case "여호수아 1":
                beginIdx = 5853;
                endIdx = 5870;
                break;
            case "Joshua 2":
            case "여호수아 2":
                beginIdx = 5871;
                endIdx = 5894;
                break;
            case "Joshua 3":
            case "여호수아 3":
                beginIdx = 5895;
                endIdx = 5911;
                break;
            case "Joshua 4":
            case "여호수아 4":
                beginIdx = 5912;
                endIdx = 5935;
                break;
            case "Joshua 5":
            case "여호수아 5":
                beginIdx = 5936;
                endIdx = 5950;
                break;
            case "Joshua 6":
            case "여호수아 6":
                beginIdx = 5951;
                endIdx = 5977;
                break;
            case "Joshua 7":
            case "여호수아 7":
                beginIdx = 5978;
                endIdx = 6003;
                break;
            case "Joshua 8":
            case "여호수아 8":
                beginIdx = 6004;
                endIdx = 6038;
                break;
            case "Joshua 9":
            case "여호수아 9":
                beginIdx = 6039;
                endIdx = 6065;
                break;
            case "Joshua 10":
            case "여호수아 10":
                beginIdx = 6066;
                endIdx = 6108;
                break;
            case "Joshua 11":
            case "여호수아 11":
                beginIdx = 6109;
                endIdx = 6131;
                break;
            case "Joshua 12":
            case "여호수아 12":
                beginIdx = 6132;
                endIdx = 6155;
                break;
            case "Joshua 13":
            case "여호수아 13":
                beginIdx = 6156;
                endIdx = 6188;
                break;
            case "Joshua 14":
            case "여호수아 14":
                beginIdx = 6189;
                endIdx = 6203;
                break;
            case "Joshua 15":
            case "여호수아 15":
                beginIdx = 6204;
                endIdx = 6266;
                break;
            case "Joshua 16":
            case "여호수아 16":
                beginIdx = 6267;
                endIdx = 6276;
                break;
            case "Joshua 17":
            case "여호수아 17":
                beginIdx = 6277;
                endIdx = 6294;
                break;
            case "Joshua 18":
            case "여호수아 18":
                beginIdx = 6295;
                endIdx = 6322;
                break;
            case "Joshua 19":
            case "여호수아 19":
                beginIdx = 6323;
                endIdx = 6373;
                break;
            case "Joshua 20":
            case "여호수아 20":
                beginIdx = 6374;
                endIdx = 6382;
                break;
            case "Joshua 21":
            case "여호수아 21":
                beginIdx = 6383;
                endIdx = 6427;
                break;
            case "Joshua 22":
            case "여호수아 22":
                beginIdx = 6428;
                endIdx = 6461;
                break;
            case "Joshua 23":
            case "여호수아 23":
                beginIdx = 6461;
                endIdx = 6477;
                break;
            case "Joshua 24":
            case "여호수아 24":
                beginIdx = 6478;
                endIdx = 6510;
                break;
        }
    }
    public void GetJudgesVerse(String sheetTitle){
        switch (sheetTitle){
            case "Judges 1":
            case "사사기 1":
                beginIdx = 6511;
                endIdx = 6546;
                break;
            case "Judges 2":
            case "사사기 2":
                beginIdx = 6547;
                endIdx = 6569;
                break;
            case "Judges 3":
            case "사사기 3":
                beginIdx = 6570;
                endIdx = 6600;
                break;
            case "Judges 4":
            case "사사기 4":
                beginIdx = 6601;
                endIdx = 6624;
                break;
            case "Judges 5":
            case "사사기 5":
                beginIdx = 6625;
                endIdx = 6655;
                break;
            case "Judges 6":
            case "사사기 6":
                beginIdx = 6656;
                endIdx = 6695;
                break;
            case "Judges 7":
            case "사사기 7":
                beginIdx = 6696;
                endIdx = 6720;
                break;
            case "Judges 8":
            case "사사기 8":
                beginIdx = 6721;
                endIdx = 6755;
                break;
            case "Judges 9":
            case "사사기 9":
                beginIdx = 6756;
                endIdx = 6812;
                break;
            case "Judges 10":
            case "사사기 10":
                beginIdx = 6813;
                endIdx = 6830;
                break;
            case "Judges 11":
            case "사사기 11":
                beginIdx = 6831;
                endIdx = 6870;
                break;
            case "Judges 12":
            case "사사기 12":
                beginIdx = 6871;
                endIdx = 6885;
                break;
            case "Judges 13":
            case "사사기 13":
                beginIdx = 6886;
                endIdx = 6910;
                break;
            case "Judges 14":
            case "사사기 14":
                beginIdx = 6911;
                endIdx = 6930;
                break;
            case "Judges 15":
            case "사사기 15":
                beginIdx = 6931;
                endIdx = 6950;
                break;
            case "Judges 16":
            case "사사기 16":
                beginIdx = 6951;
                endIdx = 6981;
                break;
            case "Judges 17":
            case "사사기 17":
                beginIdx = 6982;
                endIdx = 6994;
                break;
            case "Judges 18":
            case "사사기 18":
                beginIdx = 6995;
                endIdx = 7025;
                break;
            case "Judges 19":
            case "사사기 19":
                beginIdx = 7026;
                endIdx = 7055;
                break;
            case "Judges 20":
            case "사사기 20":
                beginIdx = 7056;
                endIdx = 7103;
                break;
            case "Judges 21":
            case "사사기 21":
                beginIdx = 7104;
                endIdx = 7128;
                break;
        }
    }
    public void GetRuthVerse(String sheetTitle){
        switch (sheetTitle){
            case "Ruth 1":
            case "룻기 1":
                beginIdx = 7129;
                endIdx = 7150;
                break;
            case "Ruth 2":
            case "룻기 2":
                beginIdx = 7151;
                endIdx = 7173;
                break;
            case "Ruth 3":
            case "룻기 3":
                beginIdx = 7174;
                endIdx = 7191;
                break;
            case "Ruth 4":
            case "룻기 4":
                beginIdx = 7192;
                endIdx = 7213;
                break;
        }
    }
    public void GetSamuel1Verse(String sheetTitle){
        switch (sheetTitle){
            case "1 Samuel 1":
            case "사무엘상 1":
                beginIdx = 7214;
                endIdx = 7241;
                break;
            case "1 Samuel 2":
            case "사무엘상 2":
                beginIdx = 7242;
                endIdx = 7277;
                break;
            case "1 Samuel 3":
            case "사무엘상 3":
                beginIdx = 7278;
                endIdx = 7298;
                break;
            case "1 Samuel 4":
            case "사무엘상 4":
                beginIdx = 7299;
                endIdx = 7320;
                break;
            case "1 Samuel 5":
            case "사무엘상 5":
                beginIdx = 7321;
                endIdx = 7332;
                break;
            case "1 Samuel 6":
            case "사무엘상 6":
                beginIdx = 7333;
                endIdx = 7353;
                break;
            case "1 Samuel 7":
            case "사무엘상 7":
                beginIdx = 7354;
                endIdx = 7370;
                break;
            case "1 Samuel 8":
            case "사무엘상 8":
                beginIdx = 7371;
                endIdx = 7392;
                break;
            case "1 Samuel 9":
            case "사무엘상 9":
                beginIdx = 7393;
                endIdx = 7419;
                break;
            case "1 Samuel 10":
            case "사무엘상 10":
                beginIdx = 7420;
                endIdx = 7446;
                break;
            case "1 Samuel 11":
            case "사무엘상 11":
                beginIdx = 7447;
                endIdx = 7461;
                break;
            case "1 Samuel 12":
            case "사무엘상 12":
                beginIdx = 7462;
                endIdx = 7486;
                break;
            case "1 Samuel 13":
            case "사무엘상 13":
                beginIdx = 7487;
                endIdx = 7509;
                break;
            case "1 Samuel 14":
            case "사무엘상 14":
                beginIdx = 7510;
                endIdx = 7561;
                break;
            case "1 Samuel 15":
            case "사무엘상 15":
                beginIdx = 7562;
                endIdx = 7596;
                break;
            case "1 Samuel 16":
            case "사무엘상 16":
                beginIdx = 7597;
                endIdx = 7619;
                break;
            case "1 Samuel 17":
            case "사무엘상 17":
                beginIdx = 7620;
                endIdx = 7677;
                break;
            case "1 Samuel 18":
            case "사무엘상 18":
                beginIdx = 7678;
                endIdx = 7707;
                break;
            case "1 Samuel 19":
            case "사무엘상 19":
                beginIdx = 7708;
                endIdx = 7731;
                break;
            case "1 Samuel 20":
            case "사무엘상 20":
                beginIdx = 7732;
                endIdx = 7773;
                break;
            case "1 Samuel 21":
            case "사무엘상 21":
                beginIdx = 7774;
                endIdx = 7788;
                break;
            case "1 Samuel 22":
            case "사무엘상 22":
                beginIdx = 7789;
                endIdx = 7811;
                break;
            case "1 Samuel 23":
            case "사무엘상 23":
                beginIdx = 7812;
                endIdx = 7840;
                break;
            case "1 Samuel 24":
            case "사무엘상 24":
                beginIdx = 7841;
                endIdx = 7862;
                break;
            case "1 Samuel 25":
            case "사무엘상 25":
                beginIdx = 7863;
                endIdx = 7906;
                break;
            case "1 Samuel 26":
            case "사무엘상 26":
                beginIdx = 7907;
                endIdx = 7931;
                break;
            case "1 Samuel 27":
            case "사무엘상 27":
                beginIdx = 7932;
                endIdx = 7943;
                break;
            case "1 Samuel 28":
            case "사무엘상 28":
                beginIdx = 7944;
                endIdx = 7968;
                break;
            case "1 Samuel 29":
            case "사무엘상 29":
                beginIdx = 7969;
                endIdx = 7979;
                break;
            case "1 Samuel 30":
            case "사무엘상 30":
                beginIdx = 7980;
                endIdx = 8010;
                break;
            case "1 Samuel 31":
            case "사무엘상 31":
                beginIdx = 8011;
                endIdx = 8023;
                break;
        }
    }
    public void GetSamuel2Verse(String sheetTitle){
        switch (sheetTitle){
            case "2 Samuel 1":
            case "사무엘하 1":
                beginIdx = 8024;
                endIdx = 8050;
                break;
            case "2 Samuel 2":
            case "사무엘하 2":
                beginIdx = 8051;
                endIdx = 8082;
                break;
            case "2 Samuel 3":
            case "사무엘하 3":
                beginIdx = 8083;
                endIdx = 8121;
                break;
            case "2 Samuel 4":
            case "사무엘하 4":
                beginIdx = 8122;
                endIdx = 8133;
                break;
            case "2 Samuel 5":
            case "사무엘하 5":
                beginIdx = 8134;
                endIdx = 8158;
                break;
            case "2 Samuel 6":
            case "사무엘하 6":
                beginIdx = 8159;
                endIdx = 8181;
                break;
            case "2 Samuel 7":
            case "사무엘하 7":
                beginIdx = 8182;
                endIdx = 8210;
                break;
            case "2 Samuel 8":
            case "사무엘하 8":
                beginIdx = 8211;
                endIdx = 8228;
                break;
            case "2 Samuel 9":
            case "사무엘하 9":
                beginIdx = 8229;
                endIdx = 8241;
                break;
            case "2 Samuel 10":
            case "사무엘하 10":
                beginIdx = 8242;
                endIdx = 8260;
                break;
            case "2 Samuel 11":
            case "사무엘하 11":
                beginIdx = 8261;
                endIdx = 8287;
                break;
            case "2 Samuel 12":
            case "사무엘하 12":
                beginIdx = 8288;
                endIdx = 8318;
                break;
            case "2 Samuel 13":
            case "사무엘하 13":
                beginIdx = 8319;
                endIdx = 8357;
                break;
            case "2 Samuel 14":
            case "사무엘하 14":
                beginIdx = 8358;
                endIdx = 8390;
                break;
            case "2 Samuel 15":
            case "사무엘하 15":
                beginIdx = 8391;
                endIdx = 8427;
                break;
            case "2 Samuel 16":
            case "사무엘하 16":
                beginIdx = 8428;
                endIdx = 8450;
                break;
            case "2 Samuel 17":
            case "사무엘하 17":
                beginIdx = 8451;
                endIdx = 8479;
                break;
            case "2 Samuel 18":
            case "사무엘하 18":
                beginIdx = 8480;
                endIdx = 8512;
                break;
            case "2 Samuel 19":
            case "사무엘하 19":
                beginIdx = 8513;
                endIdx = 8555;
                break;
            case "2 Samuel 20":
            case "사무엘하 20":
                beginIdx = 8556;
                endIdx = 8581;
                break;
            case "2 Samuel 21":
            case "사무엘하 21":
                beginIdx = 8582;
                endIdx = 8603;
                break;
            case "2 Samuel 22":
            case "사무엘하 22":
                beginIdx = 8604;
                endIdx = 8654;
                break;
            case "2 Samuel 23":
            case "사무엘하 23":
                beginIdx = 8655;
                endIdx = 8693;
                break;
            case "2 Samuel 24":
            case "사무엘하 24":
                beginIdx = 8694;
                endIdx = 8718;
                break;
        }
    }
    public void GetKing1Verse(String sheetTitle){
        switch (sheetTitle){
            case "1 King 1":
            case "열왕기상 1":
                beginIdx = 8719;
                endIdx = 8771;
                break;
            case "1 King 2":
            case "열왕기상 2":
                beginIdx = 8772;
                endIdx = 8817;
                break;
            case "1 King 3":
            case "열왕기상 3":
                beginIdx = 8818;
                endIdx = 8845;
                break;
            case "1 King 4":
            case "열왕기상 4":
                beginIdx = 8846;
                endIdx = 8879;
                break;
            case "1 King 5":
            case "열왕기상 5":
                beginIdx = 8880;
                endIdx = 8897;
                break;
            case "1 King 6":
            case "열왕기상 6":
                beginIdx = 8898;
                endIdx = 8935;
                break;
            case "1 King 7":
            case "열왕기상 7":
                beginIdx = 8936;
                endIdx = 8986;
                break;
            case "1 King 8":
            case "열왕기상 8":
                beginIdx = 8987;
                endIdx = 9052;
                break;
            case "1 King 9":
            case "열왕기상 9":
                beginIdx = 9053;
                endIdx = 9080;
                break;
            case "1 King 10":
            case "열왕기상 10":
                beginIdx = 9081;
                endIdx = 9109;
                break;
            case "1 King 11":
            case "열왕기상 11":
                beginIdx = 9110;
                endIdx = 9152;
                break;
            case "1 King 12":
            case "열왕기상 12":
                beginIdx = 9153;
                endIdx = 9158;
                break;
            case "1 King 13":
            case "열왕기상 13":
                beginIdx = 9159;
                endIdx = 9219;
                break;
            case "1 King 14":
            case "열왕기상 14":
                beginIdx = 9220;
                endIdx = 9250;
                break;
            case "1 King 15":
            case "열왕기상 15":
                beginIdx = 9251;
                endIdx = 9284;
                break;
            case "1 King 16":
            case "열왕기상 16":
                beginIdx = 9285;
                endIdx = 9318;
                break;
            case "1 King 17":
            case "열왕기상 17":
                beginIdx = 9319;
                endIdx = 9342;
                break;
            case "1 King 18":
            case "열왕기상 18":
                beginIdx = 9343;
                endIdx = 9388;
                break;
            case "1 King 19":
            case "열왕기상 19":
                beginIdx = 9389;
                endIdx = 9409;
                break;
            case "1 King 20":
            case "열왕기상 20":
                beginIdx = 9410;
                endIdx = 9452;
                break;
            case "1 King 21":
            case "열왕기상 21":
                beginIdx = 9453;
                endIdx = 9481;
                break;
            case "1 King 22":
            case "열왕기상 22":
                beginIdx = 9482;
                endIdx = 9534;
                break;
        }
    }
    public void GetKing2Verse(String sheetTitle){
        switch (sheetTitle){
            case "2 King 1":
            case "열왕기하 1":
                beginIdx = 9535;
                endIdx = 9552;
                break;
            case "2 King 2":
            case "열왕기하 2":
                beginIdx = 9553;
                endIdx = 9577;
                break;
            case "2 King 3":
            case "열왕기하 3":
                beginIdx = 9578;
                endIdx = 9604;
                break;
            case "2 King 4":
            case "열왕기하 4":
                beginIdx = 9605;
                endIdx = 9648;
                break;
            case "2 King 5":
            case "열왕기하 5":
                beginIdx = 9649;
                endIdx = 9675;
                break;
            case "2 King 6":
            case "열왕기하 6":
                beginIdx = 9676;
                endIdx = 9708;
                break;
            case "2 King 7":
            case "열왕기하 7":
                beginIdx = 9709;
                endIdx = 9728;
                break;
            case "2 King 8":
            case "열왕기하 8":
                beginIdx = 9729;
                endIdx = 9757;
                break;
            case "2 King 9":
            case "열왕기하 9":
                beginIdx = 9758;
                endIdx = 9794;
                break;
            case "2 King 10":
            case "열왕기하 10":
                beginIdx = 9795;
                endIdx = 9830;
                break;
            case "2 King 11":
            case "열왕기하 11":
                beginIdx = 9831;
                endIdx = 9851;
                break;
            case "2 King 12":
            case "열왕기하 12":
                beginIdx = 9582;
                endIdx = 9872;
                break;
            case "2 King 13":
            case "열왕기하 13":
                beginIdx = 9873;
                endIdx = 9897;
                break;
            case "2 King 14":
            case "열왕기하 14":
                beginIdx = 9898;
                endIdx = 9926;
                break;
            case "2 King 15":
            case "열왕기하 15":
                beginIdx = 9927;
                endIdx = 9964;
                break;
            case "2 King 16":
            case "열왕기하 16":
                beginIdx = 9965;
                endIdx = 9984;
                break;
            case "2 King 17":
            case "열왕기하 17":
                beginIdx = 9985;
                endIdx = 10025;
                break;
            case "2 King 18":
            case "열왕기하 18":
                beginIdx = 10026;
                endIdx = 10062;
                break;
            case "2 King 19":
            case "열왕기하 19":
                beginIdx = 10063;
                endIdx = 10099;
                break;
            case "2 King 20":
            case "열왕기하 20":
                beginIdx = 10100;
                endIdx = 10120;
                break;
            case "2 King 21":
            case "열왕기하 21":
                beginIdx = 10121;
                endIdx = 10146;
                break;
            case "2 King 22":
            case "열왕기하 22":
                beginIdx = 10147;
                endIdx = 10166;
                break;
            case "2 King 23":
            case "열왕기하 23":
                beginIdx = 10167;
                endIdx = 10203;
                break;
            case "2 King 24":
            case "열왕기하 24":
                beginIdx = 10204;
                endIdx = 10223;
                break;
            case "2 King 25":
            case "열왕기하 25":
                beginIdx = 10224;
                endIdx = 10253;
                break;
        }
    }
    public void GetChronicles1Verse(String sheetTitle){
        switch (sheetTitle){
            case "1 Chronicles 1":
            case "역대상 1":
                beginIdx = 10254;
                endIdx = 10307;
                break;
            case "1 Chronicles 2":
            case "역대상 2":
                beginIdx = 10308;
                endIdx = 10362;
                break;
            case "1 Chronicles 3":
            case "역대상 3":
                beginIdx = 10363;
                endIdx = 10386;
                break;
            case "1 Chronicles 4":
            case "역대상 4":
                beginIdx = 10387;
                endIdx = 10429;
                break;
            case "1 Chronicles 5":
            case "역대상 5":
                beginIdx = 10430;
                endIdx = 10455;
                break;
            case "1 Chronicles 6":
            case "역대상 6":
                beginIdx = 10456;
                endIdx = 10536;
                break;
            case "1 Chronicles 7":
            case "역대상 7":
                beginIdx = 10537;
                endIdx = 10576;
                break;
            case "1 Chronicles 8":
            case "역대상 8":
                beginIdx = 10577;
                endIdx = 10616;
                break;
            case "1 Chronicles 9":
            case "역대상 9":
                beginIdx = 10617;
                endIdx = 10660;
                break;
            case "1 Chronicles 10":
            case "역대상 10":
                beginIdx = 10661;
                endIdx = 10674;
                break;
            case "1 Chronicles 11":
            case "역대상 11":
                beginIdx = 10675;
                endIdx = 10721;
                break;
            case "1 Chronicles 12":
            case "역대상 12":
                beginIdx = 10722;
                endIdx = 10761;
                break;
            case "1 Chronicles 13":
            case "역대상 13":
                beginIdx = 10762;
                endIdx = 10775;
                break;
            case "1 Chronicles 14":
            case "역대상 14":
                beginIdx = 10776;
                endIdx = 10792;
                break;
            case "1 Chronicles 15":
            case "역대상 15":
                beginIdx = 10793;
                endIdx = 10821;
                break;
            case "1 Chronicles 16":
            case "역대상 16":
                beginIdx = 10822;
                endIdx = 10864;
                break;
            case "1 Chronicles 17":
            case "역대상 17":
                beginIdx = 10865;
                endIdx = 10891;
                break;
            case "1 Chronicles 18":
            case "역대상 18":
                beginIdx = 10892;
                endIdx = 10908;
                break;
            case "1 Chronicles 19":
            case "역대상 19":
                beginIdx = 10909;
                endIdx = 10927;
                break;
            case "1 Chronicles 20":
            case "역대상 20":
                beginIdx = 10928;
                endIdx = 10935;
                break;
            case "1 Chronicles 21":
            case "역대상 21":
                beginIdx = 10936;
                endIdx = 10965;
                break;
            case "1 Chronicles 22":
            case "역대상 22":
                beginIdx = 10966;
                endIdx = 10984;
                break;
            case "1 Chronicles 23":
            case "역대상 23":
                beginIdx = 10985;
                endIdx = 11016;
                break;
            case "1 Chronicles 24":
            case "역대상 24":
                beginIdx = 11017;
                endIdx = 11047;
                break;
            case "1 Chronicles 25":
            case "역대상 25":
                beginIdx = 11048;
                endIdx = 11078;
                break;
            case "1 Chronicles 26":
            case "역대상 26":
                beginIdx = 11079;
                endIdx = 11110;
                break;
            case "1 Chronicles 27":
            case "역대상 27":
                beginIdx = 11111;
                endIdx = 11144;
                break;
            case "1 Chronicles 28":
            case "역대상 28":
                beginIdx = 11145;
                endIdx = 11165;
                break;
            case "1 Chronicles 29":
            case "역대상 29":
                beginIdx = 11166;
                endIdx = 11195;
                break;
        }
    }
    public void GetChronicles2Verse(String sheetTitle){
        switch (sheetTitle){
            case "2 Chronicles 1":
            case "역대하 1":
                beginIdx = 11196;
                endIdx = 11212;
                break;
            case "2 Chronicles 2":
            case "역대하 2":
                beginIdx = 11213;
                endIdx = 11230;
                break;
            case "2 Chronicles 3":
            case "역대하 3":
                beginIdx = 11231;
                endIdx = 11247;
                break;
            case "2 Chronicles 4":
            case "역대하 4":
                beginIdx = 11248;
                endIdx = 11269;
                break;
            case "2 Chronicles 5":
            case "역대하 5":
                beginIdx = 11270;
                endIdx = 11283;
                break;
            case "2 Chronicles 6":
            case "역대하 6":
                beginIdx = 11284;
                endIdx = 11325;
                break;
            case "2 Chronicles 7":
            case "역대하 7":
                beginIdx = 11326;
                endIdx = 11347;
                break;
            case "2 Chronicles 8":
            case "역대하 8":
                beginIdx = 11348;
                endIdx = 11365;
                break;
            case "2 Chronicles 9":
            case "역대하 9":
                beginIdx = 11366;
                endIdx = 11396;
                break;
            case "2 Chronicles 10":
            case "역대하 10":
                beginIdx = 11397;
                endIdx = 11415;
                break;
            case "2 Chronicles 11":
            case "역대하 11":
                beginIdx = 11416;
                endIdx = 11438;
                break;
            case "2 Chronicles 12":
            case "역대하 12":
                beginIdx = 11439;
                endIdx = 11454;
                break;
            case "2 Chronicles 13":
            case "역대하 13":
                beginIdx = 11455;
                endIdx = 11476;
                break;
            case "2 Chronicles 14":
            case "역대하 14":
                beginIdx = 11477;
                endIdx = 11491;
                break;
            case "2 Chronicles 15":
            case "역대하 15":
                beginIdx = 11492;
                endIdx = 11510;
                break;
            case "2 Chronicles 16":
            case "역대하 16":
                beginIdx = 11511;
                endIdx = 11524;
                break;
            case "2 Chronicles 17":
            case "역대하 17":
                beginIdx = 11525;
                endIdx = 11543;
                break;
            case "2 Chronicles 18":
            case "역대하 18":
                beginIdx = 11544;
                endIdx = 11577;
                break;
            case "2 Chronicles 19":
            case "역대하 19":
                beginIdx = 11578;
                endIdx = 11588;
                break;
            case "2 Chronicles 20":
            case "역대하 20":
                beginIdx = 11589;
                endIdx = 11625;
                break;
            case "2 Chronicles 21":
            case "역대하 21":
                beginIdx = 11626;
                endIdx = 11645;
                break;
            case "2 Chronicles 22":
            case "역대하 22":
                beginIdx = 11646;
                endIdx = 11657;
                break;
            case "2 Chronicles 23":
            case "역대하 23":
                beginIdx = 11658;
                endIdx = 11678;
                break;
            case "2 Chronicles 24":
            case "역대하 24":
                beginIdx = 11679;
                endIdx = 11705;
                break;
            case "2 Chronicles 25":
            case "역대하 25":
                beginIdx = 11706;
                endIdx = 11733;
                break;
            case "2 Chronicles 26":
            case "역대하 26":
                beginIdx = 11734;
                endIdx = 11756;
                break;
            case "2 Chronicles 27":
            case "역대하 27":
                beginIdx = 11757;
                endIdx = 11765;
                break;
            case "2 Chronicles 28":
            case "역대하 28":
                beginIdx = 11766;
                endIdx = 11792;
                break;
            case "2 Chronicles 29":
            case "역대하 29":
                beginIdx = 11793;
                endIdx = 11828;
                break;
            case "2 Chronicles 30":
            case "역대하 30":
                beginIdx = 11829;
                endIdx = 11855;
                break;
            case "2 Chronicles 31":
            case "역대하 31":
                beginIdx = 11856;
                endIdx = 11876;
                break;
            case "2 Chronicles 32":
            case "역대하 32":
                beginIdx = 11877;
                endIdx = 11909;
                break;
            case "2 Chronicles 33":
            case "역대하 33":
                beginIdx = 11910;
                endIdx = 11934;
                break;
            case "2 Chronicles 34":
            case "역대하 34":
                beginIdx = 11935;
                endIdx = 11967;
                break;
            case "2 Chronicles 35":
            case "역대하 35":
                beginIdx = 11968;
                endIdx = 11994;
                break;
            case "2 Chronicles 36":
            case "역대하 36":
                beginIdx = 11995;
                endIdx = 12017;
                break;
        }
    }
    public void GetEzraVerse(String sheetTitle){
        switch (sheetTitle){
            case "Ezra 1":
            case "에스라 1":
                beginIdx = 12018;
                endIdx = 12028;
                break;
            case "Ezra 2":
            case "에스라 2":
                beginIdx = 12029;
                endIdx = 12098;
                break;
            case "Ezra 3":
            case "에스라 3":
                beginIdx = 12099;
                endIdx = 12111;
                break;
            case "Ezra 4":
            case "에스라 4":
                beginIdx = 12112;
                endIdx = 12135;
                break;
            case "Ezra 5":
            case "에스라 5":
                beginIdx = 12136;
                endIdx = 12152;
                break;
            case "Ezra 6":
            case "에스라 6":
                beginIdx = 12153;
                endIdx = 12174;
                break;
            case "Ezra 7":
            case "에스라 7":
                beginIdx = 12175;
                endIdx = 12202;
                break;
            case "Ezra 8":
            case "에스라 8":
                beginIdx = 12203;
                endIdx = 12238;
                break;
            case "Ezra 9":
            case "에스라 9":
                beginIdx = 12239;
                endIdx = 12253;
                break;
            case "Ezra 10":
            case "에스라 10":
                beginIdx = 12254;
                endIdx = 12297;
                break;
        }
    }
    public void GetNehemiahVerse(String sheetTitle){
        switch (sheetTitle){
            case "Nehemiah 1":
            case "느헤미야 1":
                beginIdx = 12298;
                endIdx = 12308;
                break;
            case "Nehemiah 2":
            case "느헤미야 2":
                beginIdx = 12309;
                endIdx = 12328;
                break;
            case "Nehemiah 3":
            case "느헤미야 3":
                beginIdx = 12329;
                endIdx = 12360;
                break;
            case "Nehemiah 4":
            case "느헤미야 4":
                beginIdx = 12361;
                endIdx = 12383;
                break;
            case "Nehemiah 5":
            case "느헤미야 5":
                beginIdx = 12384;
                endIdx = 12402;
                break;
            case "Nehemiah 6":
            case "느헤미야 6":
                beginIdx = 12403;
                endIdx = 12421;
                break;
            case "Nehemiah 7":
            case "느헤미야 7":
                beginIdx = 12422;
                endIdx = 12494;
                break;
            case "Nehemiah 8":
            case "느헤미야 8":
                beginIdx = 12495;
                endIdx = 12512;
                break;
            case "Nehemiah 9":
            case "느헤미야 9":
                beginIdx = 12513;
                endIdx = 12550;
                break;
            case "Nehemiah 10":
            case "느헤미야 10":
                beginIdx = 12551;
                endIdx = 12589;
                break;
            case "Nehemiah 11":
            case "느헤미야 11":
                beginIdx = 12590;
                endIdx = 12625;
                break;
            case "Nehemiah 12":
            case "느헤미야 12":
                beginIdx = 12626;
                endIdx = 12672;
                break;
            case "Nehemiah 13":
            case "느헤미야 13":
                beginIdx = 12673;
                endIdx = 12703;
                break;
        }
    }
    public void GetEstherVerse(String sheetTitle){
        switch (sheetTitle){
            case "Esther 1":
            case "에스더 1":
                beginIdx = 12704;
                endIdx = 12725;
                break;
            case "Esther 2":
            case "에스더 2":
                beginIdx = 12726;
                endIdx = 12748;
                break;
            case "Esther 3":
            case "에스더 3":
                beginIdx = 12749;
                endIdx = 12763;
                break;
            case "Esther 4":
            case "에스더 4":
                beginIdx = 12764;
                endIdx = 12780;
                break;
            case "Esther 5":
            case "에스더 5":
                beginIdx = 12781;
                endIdx = 12794;
                break;
            case "Esther 6":
            case "에스더 6":
                beginIdx = 12795;
                endIdx = 12808;
                break;
            case "Esther 7":
            case "에스더 7":
                beginIdx = 12809;
                endIdx = 12818;
                break;
            case "Esther 8":
            case "에스더 8":
                beginIdx = 12819;
                endIdx = 12835;
                break;
            case "Esther 9":
            case "에스더 9":
                beginIdx = 12836;
                endIdx = 12867;
                break;
            case "Esther 10":
            case "에스더 10":
                beginIdx = 12868;
                endIdx = 12870;
                break;
        }
    }
    public void GetJobVerse(String sheetTitle){
        switch (sheetTitle){
            case "Job 1":
            case "욥기 1":
                beginIdx = 12871;
                endIdx = 12892;
                break;
            case "Job 2":
            case "욥기 2":
                beginIdx = 12893;
                endIdx = 12905;
                break;
            case "Job 3":
            case "욥기 3":
                beginIdx = 12906;
                endIdx = 12931;
                break;
            case "Job 4":
            case "욥기 4":
                beginIdx = 12932;
                endIdx = 12952;
                break;
            case "Job 5":
            case "욥기 5":
                beginIdx = 12953;
                endIdx = 12979;
                break;
            case "Job 6":
            case "욥기 6":
                beginIdx = 12980;
                endIdx = 13009;
                break;
            case "Job 7":
            case "욥기 7":
                beginIdx = 13010;
                endIdx = 13030;
                break;
            case "Job 8":
            case "욥기 8":
                beginIdx = 13031;
                endIdx = 13052;
                break;
            case "Job 9":
            case "욥기 9":
                beginIdx = 13053;
                endIdx = 13087;
                break;
            case "Job 10":
            case "욥기 10":
                beginIdx = 13088;
                endIdx = 13109;
                break;
            case "Job 11":
            case "욥기 11":
                beginIdx = 13110;
                endIdx = 13129;
                break;
            case "Job 12":
            case "욥기 12":
                beginIdx = 13130;
                endIdx = 13154;
                break;
            case "Job 13":
            case "욥기 13":
                beginIdx = 13155;
                endIdx = 13182;
                break;
            case "Job 14":
            case "욥기 14":
                beginIdx = 13183;
                endIdx = 13204;
                break;
            case "Job 15":
            case "욥기 15":
                beginIdx = 13205;
                endIdx = 13239;
                break;
            case "Job 16":
            case "욥기 16":
                beginIdx = 13240;
                endIdx = 13261;
                break;
            case "Job 17":
            case "욥기 17":
                beginIdx = 13262;
                endIdx = 13277;
                break;
            case "Job 18":
            case "욥기 18":
                beginIdx = 13278;
                endIdx = 13298;
                break;
            case "Job 19":
            case "욥기 19":
                beginIdx = 13299;
                endIdx = 13327;
                break;
            case "Job 20":
            case "욥기 20":
                beginIdx = 13328;
                endIdx = 13356;
                break;
            case "Job 21":
            case "욥기 21":
                beginIdx = 13357;
                endIdx = 13390;
                break;
            case "Job 22":
            case "욥기 22":
                beginIdx = 13391;
                endIdx = 13420;
                break;
            case "Job 23":
            case "욥기 23":
                beginIdx = 13421;
                endIdx = 13437;
                break;
            case "Job 24":
            case "욥기 24":
                beginIdx = 13438;
                endIdx = 13462;
                break;
            case "Job 25":
            case "욥기 25":
                beginIdx = 13463;
                endIdx = 13468;
                break;
            case "Job 26":
            case "욥기 26":
                beginIdx = 13469;
                endIdx = 13482;
                break;
            case "Job 27":
            case "욥기 27":
                beginIdx = 13483;
                endIdx = 13505;
                break;
            case "Job 28":
            case "욥기 28":
                beginIdx = 13506;
                endIdx = 13533;
                break;
            case "Job 29":
            case "욥기 29":
                beginIdx = 13534;
                endIdx = 13558;
                break;
            case "Job 30":
            case "욥기 30":
                beginIdx = 13559;
                endIdx = 13589;
                break;
            case "Job 31":
            case "욥기 31":
                beginIdx = 13590;
                endIdx = 13629;
                break;
            case "Job 32":
            case "욥기 32":
                beginIdx = 13630;
                endIdx = 13651;
                break;
            case "Job 33":
            case "욥기 33":
                beginIdx = 13652;
                endIdx = 13684;
                break;
            case "Job 34":
            case "욥기 34":
                beginIdx = 13685;
                endIdx = 13721;
                break;
            case "Job 35":
            case "욥기 35":
                beginIdx = 13722;
                endIdx = 13737;
                break;
            case "Job 36":
            case "욥기 36":
                beginIdx = 13738;
                endIdx = 13770;
                break;
            case "Job 37":
            case "욥기 37":
                beginIdx = 13771;
                endIdx = 13794;
                break;
            case "Job 38":
            case "욥기 38":
                beginIdx = 13795;
                endIdx = 13835;
                break;
            case "Job 39":
            case "욥기 39":
                beginIdx = 13836;
                endIdx = 13865;
                break;
            case "Job 40":
            case "욥기 40":
                beginIdx = 13866;
                endIdx = 13889;
                break;
            case "Job 41":
            case "욥기 41":
                beginIdx = 13890;
                endIdx = 13923;
                break;
            case "Job 42":
            case "욥기 42":
                beginIdx = 13924;
                endIdx = 13940;
                break;
        }
    }
    public void GetPsalmsVerse(String sheetTitle){
        switch (sheetTitle){
            case "Psalms 1":
            case "시편 1":
                beginIdx = 13941;
                endIdx = 13946;
                break;
            case "Psalms 2":
            case "시편 2":
                beginIdx = 13947;
                endIdx = 13958;
                break;
            case "Psalms 3":
            case "시편 3":
                beginIdx = 13959;
                endIdx = 13966;
                break;
            case "Psalms 4":
            case "시편 4":
                beginIdx = 13967;
                endIdx = 13974;
                break;
            case "Psalms 5":
            case "시편 5":
                beginIdx = 13975;
                endIdx = 13986;
                break;
            case "Psalms 6":
            case "시편 6":
                beginIdx = 13987;
                endIdx = 13996;
                break;
            case "Psalms 7":
            case "시편 7":
                beginIdx = 13997;
                endIdx = 14013;
                break;
            case "Psalms 8":
            case "시편 8":
                beginIdx = 14014;
                endIdx = 14022;
                break;
            case "Psalms 9":
            case "시편 9":
                beginIdx = 14023;
                endIdx = 14042;
                break;
            case "Psalms 10":
            case "시편 10":
                beginIdx = 14043;
                endIdx = 14060;
                break;
            case "Psalms 11":
            case "시편 11":
                beginIdx = 14061;
                endIdx = 14067;
                break;
            case "Psalms 12":
            case "시편 12":
                beginIdx = 14068;
                endIdx = 14075;
                break;
            case "Psalms 13":
            case "시편 13":
                beginIdx = 14076;
                endIdx = 14081;
                break;
            case "Psalms 14":
            case "시편 14":
                beginIdx = 14082;
                endIdx = 14088;
                break;
            case "Psalms 15":
            case "시편 15":
                beginIdx = 14089;
                endIdx = 14093;
                break;
            case "Psalms 16":
            case "시편 16":
                beginIdx = 14094;
                endIdx = 14104;
                break;
            case "Psalms 17":
            case "시편 17":
                beginIdx = 14105;
                endIdx = 14119;
                break;
            case "Psalms 18":
            case "시편 18":
                beginIdx = 14120;
                endIdx = 14169;
                break;
            case "Psalms 19":
            case "시편 19":
                beginIdx = 14170;
                endIdx = 14183;
                break;
            case "Psalms 20":
            case "시편 20":
                beginIdx = 14184;
                endIdx = 14192;
                break;
            case "Psalms 21":
            case "시편 21":
                beginIdx = 14193;
                endIdx = 14205;
                break;
            case "Psalms 22":
            case "시편 22":
                beginIdx = 14206;
                endIdx = 14236;
                break;
            case "Psalms 23":
            case "시편 23":
                beginIdx = 14237;
                endIdx = 14242;
                break;
            case "Psalms 24":
            case "시편 24":
                beginIdx = 14243;
                endIdx = 14252;
                break;
            case "Psalms 25":
            case "시편 25":
                beginIdx = 14253;
                endIdx = 14274;
                break;
            case "Psalms 26":
            case "시편 26":
                beginIdx = 14275;
                endIdx = 14286;
                break;
            case "Psalms 27":
            case "시편 27":
                beginIdx = 14287;
                endIdx = 14300;
                break;
            case "Psalms 28":
            case "시편 28":
                beginIdx = 14301;
                endIdx = 14309;
                break;
            case "Psalms 29":
            case "시편 29":
                beginIdx = 14310;
                endIdx = 14320;
                break;
            case "Psalms 30":
            case "시편 30":
                beginIdx = 14321;
                endIdx = 14332;
                break;
            case "Psalms 31":
            case "시편 31":
                beginIdx = 14333;
                endIdx = 14356;
                break;
            case "Psalms 32":
            case "시편 32":
                beginIdx = 14357;
                endIdx = 14367;
                break;
            case "Psalms 33":
            case "시편 33":
                beginIdx = 14368;
                endIdx = 14389;
                break;
            case "Psalms 34":
            case "시편 34":
                beginIdx = 14390;
                endIdx = 14411;
                break;
            case "Psalms 35":
            case "시편 35":
                beginIdx = 14412;
                endIdx = 14439;
                break;
            case "Psalms 36":
            case "시편 36":
                beginIdx = 14440;
                endIdx = 14451;
                break;
            case "Psalms 37":
            case "시편 37":
                beginIdx = 14452;
                endIdx = 14491;
                break;
            case "Psalms 38":
            case "시편 38":
                beginIdx = 14492;
                endIdx = 14513;
                break;
            case "Psalms 39":
            case "시편 39":
                beginIdx = 14514;
                endIdx = 14526;
                break;
            case "Psalms 40":
            case "시편 40":
                beginIdx = 14527;
                endIdx = 14543;
                break;
            case "Psalms 41":
            case "시편 41":
                beginIdx = 14544;
                endIdx = 14556;
                break;
            case "Psalms 42":
            case "시편 42":
                beginIdx = 14557;
                endIdx = 14567;
                break;
            case "Psalms 43":
            case "시편 43":
                beginIdx = 14568;
                endIdx = 14572;
                break;
            case "Psalms 44":
            case "시편 44":
                beginIdx = 14573;
                endIdx = 14598;
                break;
            case "Psalms 45":
            case "시편 45":
                beginIdx = 14599;
                endIdx = 14615;
                break;
            case "Psalms 46":
            case "시편 46":
                beginIdx = 14616;
                endIdx = 14626;
                break;
            case "Psalms 47":
            case "시편 47":
                beginIdx = 14627;
                endIdx = 14635;
                break;
            case "Psalms 48":
            case "시편 48":
                beginIdx = 14636;
                endIdx = 14649;
                break;
            case "Psalms 49":
            case "시편 49":
                beginIdx = 14650;
                endIdx = 14669;
                break;
            case "Psalms 50":
            case "시편 50":
                beginIdx = 14670;
                endIdx = 14692;
                break;
            case "Psalms 51":
            case "시편 51":
                beginIdx = 14693;
                endIdx = 14711;
                break;
            case "Psalms 52":
            case "시편 52":
                beginIdx = 14712;
                endIdx = 14720;
                break;
            case "Psalms 53":
            case "시편 53":
                beginIdx = 14721;
                endIdx = 14726;
                break;
            case "Psalms 54":
            case "시편 54":
                beginIdx = 14727;
                endIdx = 14733;
                break;
            case "Psalms 55":
            case "시편 55":
                beginIdx = 14734;
                endIdx = 14756;
                break;
            case "Psalms 56":
            case "시편 56":
                beginIdx = 14757;
                endIdx = 14769;
                break;
            case "Psalms 57":
            case "시편 57":
                beginIdx = 14770;
                endIdx = 14780;
                break;
            case "Psalms 58":
            case "시편 58":
                beginIdx = 14781;
                endIdx = 14791;
                break;
            case "Psalms 59":
            case "시편 59":
                beginIdx = 14792;
                endIdx = 14808;
                break;
            case "Psalms 60":
            case "시편 60":
                beginIdx = 14809;
                endIdx = 14820;
                break;
            case "Psalms 61":
            case "시편 61":
                beginIdx = 14821;
                endIdx = 14828;
                break;
            case "Psalms 62":
            case "시편 62":
                beginIdx = 14829;
                endIdx = 14840;
                break;
            case "Psalms 63":
            case "시편 63":
                beginIdx = 14841;
                endIdx = 14851;
                break;
            case "Psalms 64":
            case "시편 64":
                beginIdx = 14852;
                endIdx = 14861;
                break;
            case "Psalms 65":
            case "시편 65":
                beginIdx = 14862;
                endIdx = 14874;
                break;
            case "Psalms 66":
            case "시편 66":
                beginIdx = 14875;
                endIdx = 14894;
                break;
            case "Psalms 67":
            case "시편 67":
                beginIdx = 14895;
                endIdx = 14901;
                break;
            case "Psalms 68":
            case "시편 68":
                beginIdx = 14902;
                endIdx = 14936;
                break;
            case "Psalms 69":
            case "시편 69":
                beginIdx = 14937;
                endIdx = 14972;
                break;
            case "Psalms 70":
            case "시편 70":
                beginIdx = 14973;
                endIdx = 14977;
                break;
            case "Psalms 71":
            case "시편 71":
                beginIdx = 14978;
                endIdx = 15001;
                break;
            case "Psalms 72":
            case "시편 72":
                beginIdx = 15002;
                endIdx = 15021;
                break;
            case "Psalms 73":
            case "시편 73":
                beginIdx = 15022;
                endIdx = 15049;
                break;
            case "Psalms 74":
            case "시편 74":
                beginIdx = 15050;
                endIdx = 15072;
                break;
            case "Psalms 75":
            case "시편 75":
                beginIdx = 15073;
                endIdx = 15082;
                break;
            case "Psalms 76":
            case "시편 76":
                beginIdx = 15083;
                endIdx = 15094;
                break;
            case "Psalms 77":
            case "시편 77":
                beginIdx = 15095;
                endIdx = 15114;
                break;
            case "Psalms 78":
            case "시편 78":
                beginIdx = 15115;
                endIdx = 15186;
                break;
            case "Psalms 79":
            case "시편 79":
                beginIdx = 15187;
                endIdx = 15199;
                break;
            case "Psalms 80":
            case "시편 80":
                beginIdx = 15200;
                endIdx = 15218;
                break;
            case "Psalms 81":
            case "시편 81":
                beginIdx = 15219;
                endIdx = 15234;
                break;
            case "Psalms 82":
            case "시편 82":
                beginIdx = 15235;
                endIdx = 15242;
                break;
            case "Psalms 83":
            case "시편 83":
                beginIdx = 15243;
                endIdx = 15260;
                break;
            case "Psalms 84":
            case "시편 84":
                beginIdx = 15261;
                endIdx = 15272;
                break;
            case "Psalms 85":
            case "시편 85":
                beginIdx = 15273;
                endIdx = 15285;
                break;
            case "Psalms 86":
            case "시편 86":
                beginIdx = 15286;
                endIdx = 15302;
                break;
            case "Psalms 87":
            case "시편 87":
                beginIdx = 15303;
                endIdx = 15309;
                break;
            case "Psalms 88":
            case "시편 88":
                beginIdx = 15310;
                endIdx = 15327;
                break;
            case "Psalms 89":
            case "시편 89":
                beginIdx = 15328;
                endIdx = 15379;
                break;
            case "Psalms 90":
            case "시편 90":
                beginIdx = 15380;
                endIdx = 15396;
                break;
            case "Psalms 91":
            case "시편 91":
                beginIdx = 15397;
                endIdx = 15412;
                break;
            case "Psalms 92":
            case "시편 92":
                beginIdx = 15413;
                endIdx = 15427;
                break;
            case "Psalms 93":
            case "시편 93":
                beginIdx = 15428;
                endIdx = 15432;
                break;
            case "Psalms 94":
            case "시편 94":
                beginIdx = 15433;
                endIdx = 15455;
                break;
            case "Psalms 95":
            case "시편 95":
                beginIdx = 15456;
                endIdx = 15466;
                break;
            case "Psalms 96":
            case "시편 96":
                beginIdx = 15467;
                endIdx = 15479;
                break;
            case "Psalms 97":
            case "시편 97":
                beginIdx = 15480;
                endIdx = 15491;
                break;
            case "Psalms 98":
            case "시편 98":
                beginIdx = 15492;
                endIdx = 15500;
                break;
            case "Psalms 99":
            case "시편 99":
                beginIdx = 15501;
                endIdx = 15509;
                break;
            case "Psalms 100":
            case "시편 100":
                beginIdx = 15510;
                endIdx = 15514;
                break;
            case "Psalms 101":
            case "시편 101":
                beginIdx = 15515;
                endIdx = 15522;
                break;
            case "Psalms 102":
            case "시편 102":
                beginIdx = 15523;
                endIdx = 15550;
                break;
            case "Psalms 103":
            case "시편 103":
                beginIdx = 15551;
                endIdx = 15572;
                break;
            case "Psalms 104":
            case "시편 104":
                beginIdx = 15573;
                endIdx = 15607;
                break;
            case "Psalms 105":
            case "시편 105":
                beginIdx = 15608;
                endIdx = 15652;
                break;
            case "Psalms 106":
            case "시편 106":
                beginIdx = 15653;
                endIdx = 15700;
                break;
            case "Psalms 107":
            case "시편 107":
                beginIdx = 15701;
                endIdx = 15743;
                break;
            case "Psalms 108":
            case "시편 108":
                beginIdx = 15744;
                endIdx = 15756;
                break;
            case "Psalms 109":
            case "시편 109":
                beginIdx = 15757;
                endIdx = 15787;
                break;
            case "Psalms 110":
            case "시편 110":
                beginIdx = 15788;
                endIdx = 15794;
                break;
            case "Psalms 111":
            case "시편 111":
                beginIdx = 15795;
                endIdx = 15804;
                break;
            case "Psalms 112":
            case "시편 112":
                beginIdx = 15805;
                endIdx = 15814;
                break;
            case "Psalms 113":
            case "시편 113":
                beginIdx = 15815;
                endIdx = 15823;
                break;
            case "Psalms 114":
            case "시편 114":
                beginIdx = 15824;
                endIdx = 15831;
                break;
            case "Psalms 115":
            case "시편 115":
                beginIdx = 15832;
                endIdx = 15849;
                break;
            case "Psalms 116":
            case "시편 116":
                beginIdx = 15850;
                endIdx = 15868;
                break;
            case "Psalms 117":
            case "시편 117":
                beginIdx = 15869;
                endIdx = 15870;
                break;
            case "Psalms 118":
            case "시편 118":
                beginIdx = 15871;
                endIdx = 15899;
                break;
            case "Psalms 119":
            case "시편 119":
                beginIdx = 15900;
                endIdx = 16075;
                break;
            case "Psalms 120":
            case "시편 120":
                beginIdx = 16076;
                endIdx = 16082;
                break;
            case "Psalms 121":
            case "시편 121":
                beginIdx = 16083;
                endIdx = 16090;
                break;
            case "Psalms 122":
            case "시편 122":
                beginIdx = 16091;
                endIdx = 16099;
                break;
            case "Psalms 123":
            case "시편 123":
                beginIdx = 16100;
                endIdx = 16103;
                break;
            case "Psalms 124":
            case "시편 124":
                beginIdx = 16104;
                endIdx = 16111;
                break;
            case "Psalms 125":
            case "시편 125":
                beginIdx = 16112;
                endIdx = 16116;
                break;
            case "Psalms 126":
            case "시편 126":
                beginIdx = 16117;
                endIdx = 16122;
                break;
            case "Psalms 127":
            case "시편 127":
                beginIdx = 16123;
                endIdx = 16127;
                break;
            case "Psalms 128":
            case "시편 128":
                beginIdx = 16128;
                endIdx = 16133;
                break;
            case "Psalms 129":
            case "시편 129":
                beginIdx = 16134;
                endIdx = 16141;
                break;
            case "Psalms 130":
            case "시편 130":
                beginIdx = 16142;
                endIdx = 16149;
                break;
            case "Psalms 131":
            case "시편 131":
                beginIdx = 16150;
                endIdx = 16152;
                break;
            case "Psalms 132":
            case "시편 132":
                beginIdx = 16153;
                endIdx = 16170;
                break;
            case "Psalms 133":
            case "시편 133":
                beginIdx = 16171;
                endIdx = 16173;
                break;
            case "Psalms 134":
            case "시편 134":
                beginIdx = 16174;
                endIdx = 16176;
                break;
            case "Psalms 135":
            case "시편 135":
                beginIdx = 16177;
                endIdx = 16197;
                break;
            case "Psalms 136":
            case "시편 136":
                beginIdx = 16198;
                endIdx = 16223;
                break;
            case "Psalms 137":
            case "시편 137":
                beginIdx = 16224;
                endIdx = 16232;
                break;
            case "Psalms 138":
            case "시편 138":
                beginIdx = 16233;
                endIdx = 16240;
                break;
            case "Psalms 139":
            case "시편 139":
                beginIdx = 16241;
                endIdx = 16264;
                break;
            case "Psalms 140":
            case "시편 140":
                beginIdx = 16265;
                endIdx = 16277;
                break;
            case "Psalms 141":
            case "시편 141":
                beginIdx = 16278;
                endIdx = 16287;
                break;
            case "Psalms 142":
            case "시편 142":
                beginIdx = 16288;
                endIdx = 16294;
                break;
            case "Psalms 143":
            case "시편 143":
                beginIdx = 16295;
                endIdx = 16306;
                break;
            case "Psalms 144":
            case "시편 144":
                beginIdx = 16307;
                endIdx = 16321;
                break;
            case "Psalms 145":
            case "시편 145":
                beginIdx = 16322;
                endIdx = 16342;
                break;
            case "Psalms 146":
            case "시편 146":
                beginIdx = 16343;
                endIdx = 16352;
                break;
            case "Psalms 147":
            case "시편 147":
                beginIdx = 16353;
                endIdx = 16372;
                break;
            case "Psalms 148":
            case "시편 148":
                beginIdx = 16373;
                endIdx = 16386;
                break;
            case "Psalms 149":
            case "시편 149":
                beginIdx = 16387;
                endIdx = 16395;
                break;
            case "Psalms 150":
            case "시편 150":
                beginIdx = 16396;
                endIdx = 16401;
                break;
        }
    }
    public void GetProverbsVerse(String sheetTitle){
        switch (sheetTitle){
            case "Proverbs 1":
            case "잠언 1":
                beginIdx = 16402;
                endIdx = 16434;
                break;
            case "Proverbs 2":
            case "잠언 2":
                beginIdx = 16435;
                endIdx = 16456;
                break;
            case "Proverbs 3":
            case "잠언 3":
                beginIdx = 16457;
                endIdx = 16491;
                break;
            case "Proverbs 4":
            case "잠언 4":
                beginIdx = 16492;
                endIdx = 16518;
                break;
            case "Proverbs 5":
            case "잠언 5":
                beginIdx = 16519;
                endIdx = 16541;
                break;
            case "Proverbs 6":
            case "잠언 6":
                beginIdx = 16542;
                endIdx = 16576;
                break;
            case "Proverbs 7":
            case "잠언 7":
                beginIdx = 16577;
                endIdx = 16603;
                break;
            case "Proverbs 8":
            case "잠언 8":
                beginIdx = 16604;
                endIdx = 16639;
                break;
            case "Proverbs 9":
            case "잠언 9":
                beginIdx = 16640;
                endIdx = 16657;
                break;
            case "Proverbs 10":
            case "잠언 10":
                beginIdx = 16658;
                endIdx = 16689;
                break;
            case "Proverbs 11":
            case "잠언 11":
                beginIdx = 16690;
                endIdx = 16720;
                break;
            case "Proverbs 12":
            case "잠언 12":
                beginIdx = 16721;
                endIdx = 16748;
                break;
            case "Proverbs 13":
            case "잠언 13":
                beginIdx = 16749;
                endIdx = 16773;
                break;
            case "Proverbs 14":
            case "잠언 14":
                beginIdx = 16774;
                endIdx = 16808;
                break;
            case "Proverbs 15":
            case "잠언 15":
                beginIdx = 16809;
                endIdx = 16841;
                break;
            case "Proverbs 16":
            case "잠언 16":
                beginIdx = 16842;
                endIdx = 16874;
                break;
            case "Proverbs 17":
            case "잠언 17":
                beginIdx = 16875;
                endIdx = 16902;
                break;
            case "Proverbs 18":
            case "잠언 18":
                beginIdx = 16903;
                endIdx = 16926;
                break;
            case "Proverbs 19":
            case "잠언 19":
                beginIdx = 16927;
                endIdx = 16955;
                break;
            case "Proverbs 20":
            case "잠언 20":
                beginIdx = 16956;
                endIdx = 16985;
                break;
            case "Proverbs 21":
            case "잠언 21":
                beginIdx = 16986;
                endIdx = 17016;
                break;
            case "Proverbs 22":
            case "잠언 22":
                beginIdx = 17017;
                endIdx = 17045;
                break;
            case "Proverbs 23":
            case "잠언 23":
                beginIdx = 17046;
                endIdx = 17080;
                break;
            case "Proverbs 24":
            case "잠언 24":
                beginIdx = 17081;
                endIdx = 17114;
                break;
            case "Proverbs 25":
            case "잠언 25":
                beginIdx = 17115;
                endIdx = 17142;
                break;
            case "Proverbs 26":
            case "잠언 26":
                beginIdx = 17143;
                endIdx = 17170;
                break;
            case "Proverbs 27":
            case "잠언 27":
                beginIdx = 17171;
                endIdx = 17197;
                break;
            case "Proverbs 28":
            case "잠언 28":
                beginIdx = 17198;
                endIdx = 17225;
                break;
            case "Proverbs 29":
            case "잠언 29":
                beginIdx = 17226;
                endIdx = 17252;
                break;
            case "Proverbs 30":
            case "잠언 30":
                beginIdx = 17253;
                endIdx = 17285;
                break;
            case "Proverbs 31":
            case "잠언 31":
                beginIdx = 17286;
                endIdx = 17316;
                break;
        }

    }
    public void GetEcclesiastesVerse(String sheetTitle){
        switch (sheetTitle){
            case "Ecclesiastes 1":
            case "전도서 1":
                beginIdx = 17317;
                endIdx = 17334;
                break;
            case "Ecclesiastes 2":
            case "전도서 2":
                beginIdx = 17335;
                endIdx = 17360;
                break;
            case "Ecclesiastes 3":
            case "전도서 3":
                beginIdx = 17361;
                endIdx = 17382;
                break;
            case "Ecclesiastes 4":
            case "전도서 4":
                beginIdx = 17383;
                endIdx = 17398;
                break;
            case "Ecclesiastes 5":
            case "전도서 5":
                beginIdx = 17399;
                endIdx = 17418;
                break;
            case "Ecclesiastes 6":
            case "전도서 6":
                beginIdx = 17419;
                endIdx = 17430;
                break;
            case "Ecclesiastes 7":
            case "전도서 7":
                beginIdx = 17431;
                endIdx = 17459;
                break;
            case "Ecclesiastes 8":
            case "전도서 8":
                beginIdx = 17460;
                endIdx = 17476;
                break;
            case "Ecclesiastes 9":
            case "전도서 9":
                beginIdx = 17477;
                endIdx = 17494;
                break;
            case "Ecclesiastes 10":
            case "전도서 10":
                beginIdx = 17495;
                endIdx = 17514;
                break;
            case "Ecclesiastes 11":
            case "전도서 11":
                beginIdx = 17515;
                endIdx = 17524;
                break;
            case "Ecclesiastes 12":
            case "전도서 12":
                beginIdx = 17525;
                endIdx = 17538;
                break;
        }
    }
    public void GetSolomonVerse(String sheetTitle){
        switch (sheetTitle){
            case "Song of Solomon 1":
            case "아가 1":
                beginIdx = 17539;
                endIdx = 17555;
                break;
            case "Song of Solomon 2":
            case "아가 2":
                beginIdx = 17556;
                endIdx = 17572;
                break;
            case "Song of Solomon 3":
            case "아가 3":
                beginIdx = 17573;
                endIdx = 17583;
                break;
            case "Song of Solomon 4":
            case "아가 4":
                beginIdx = 17584;
                endIdx = 17599;
                break;
            case "Song of Solomon 5":
            case "아가 5":
                beginIdx = 17600;
                endIdx = 17615;
                break;
            case "Song of Solomon 6":
            case "아가 6":
                beginIdx = 17616;
                endIdx = 17628;
                break;
            case "Song of Solomon 7":
            case "아가 7":
                beginIdx = 17629;
                endIdx = 17641;
                break;
            case "Song of Solomon 8":
            case "아가 8":
                beginIdx = 17642;
                endIdx = 17655;
                break;
        }
    }
    public void GetIsaiahVerse(String sheetTitle){
        switch (sheetTitle){
            case "Isaiah 1":
            case "이사야 1":
                beginIdx = 17656;
                endIdx = 17686;
                break;
            case "Isaiah 2":
            case "이사야 2":
                beginIdx = 17687;
                endIdx = 17708;
                break;
            case "Isaiah 3":
            case "이사야 3":
                beginIdx = 17709;
                endIdx = 17734;
                break;
            case "Isaiah 4":
            case "이사야 4":
                beginIdx = 17735;
                endIdx = 17740;
                break;
            case "Isaiah 5":
            case "이사야 5":
                beginIdx = 17741;
                endIdx = 17770;
                break;
            case "Isaiah 6":
            case "이사야 6":
                beginIdx = 17771;
                endIdx = 17783;
                break;
            case "Isaiah 7":
            case "이사야 7":
                beginIdx = 17784;
                endIdx = 17808;
                break;
            case "Isaiah 8":
            case "이사야 8":
                beginIdx = 17809;
                endIdx = 17830;
                break;
            case "Isaiah 9":
            case "이사야 9":
                beginIdx = 17831;
                endIdx = 17851;
                break;
            case "Isaiah 10":
            case "이사야 10":
                beginIdx = 17852;
                endIdx = 17885;
                break;
            case "Isaiah 11":
            case "이사야 11":
                beginIdx = 17886;
                endIdx = 17901;
                break;
            case "Isaiah 12":
            case "이사야 12":
                beginIdx = 17902;
                endIdx = 17907;
                break;
            case "Isaiah 13":
            case "이사야 13":
                beginIdx = 17908;
                endIdx = 17929;
                break;
            case "Isaiah 14":
            case "이사야 14":
                beginIdx = 17930;
                endIdx = 17961;
                break;
            case "Isaiah 15":
            case "이사야 15":
                beginIdx = 17962;
                endIdx = 17970;
                break;
            case "Isaiah 16":
            case "이사야 16":
                beginIdx = 17971;
                endIdx = 17984;
                break;
            case "Isaiah 17":
            case "이사야 17":
                beginIdx = 17985;
                endIdx = 17998;
                break;
            case "Isaiah 18":
            case "이사야 18":
                beginIdx = 17999;
                endIdx = 18005;
                break;
            case "Isaiah 19":
            case "이사야 19":
                beginIdx = 18006;
                endIdx = 18030;
                break;
            case "Isaiah 20":
            case "이사야 20":
                beginIdx = 18031;
                endIdx = 18036;
                break;
            case "Isaiah 21":
            case "이사야 21":
                beginIdx = 18037;
                endIdx = 18053;
                break;
            case "Isaiah 22":
            case "이사야 22":
                beginIdx = 18054;
                endIdx = 18078;
                break;
            case "Isaiah 23":
            case "이사야 23":
                beginIdx = 18079;
                endIdx = 18096;
                break;
            case "Isaiah 24":
            case "이사야 24":
                beginIdx = 18097;
                endIdx = 18119;
                break;
            case "Isaiah 25":
            case "이사야 25":
                beginIdx = 18120;
                endIdx = 18131;
                break;
            case "Isaiah 26":
            case "이사야 26":
                beginIdx = 18132;
                endIdx = 18152;
                break;
            case "Isaiah 27":
            case "이사야 27":
                beginIdx = 18153;
                endIdx = 18165;
                break;
            case "Isaiah 28":
            case "이사야 28":
                beginIdx = 18166;
                endIdx = 18194;
                break;
            case "Isaiah 29":
            case "이사야 29":
                beginIdx = 18195;
                endIdx = 18218;
                break;
            case "Isaiah 30":
            case "이사야 30":
                beginIdx = 18219;
                endIdx = 18251;
                break;
            case "Isaiah 31":
            case "이사야 31":
                beginIdx = 18252;
                endIdx = 18260;
                break;
            case "Isaiah 32":
            case "이사야 32":
                beginIdx = 18261;
                endIdx = 18280;
                break;
            case "Isaiah 33":
            case "이사야 33":
                beginIdx = 18281;
                endIdx = 18304;
                break;
            case "Isaiah 34":
            case "이사야 34":
                beginIdx = 18305;
                endIdx = 18321;
                break;
            case "Isaiah 35":
            case "이사야 35":
                beginIdx = 18322;
                endIdx = 18331;
                break;
            case "Isaiah 36":
            case "이사야 36":
                beginIdx = 18332;
                endIdx = 18353;
                break;
            case "Isaiah 37":
            case "이사야 37":
                beginIdx = 18354;
                endIdx = 18391;
                break;
            case "Isaiah 38":
            case "이사야 38":
                beginIdx = 18392;
                endIdx = 18413;
                break;
            case "Isaiah 39":
            case "이사야 39":
                beginIdx = 18414;
                endIdx = 18421;
                break;
            case "Isaiah 40":
            case "이사야 40":
                beginIdx = 18422;
                endIdx = 18452;
                break;
            case "Isaiah 41":
            case "이사야 41":
                beginIdx = 18453;
                endIdx = 18481;
                break;
            case "Isaiah 42":
            case "이사야 42":
                beginIdx = 18482;
                endIdx = 18506;
                break;
            case "Isaiah 43":
            case "이사야 43":
                beginIdx = 18507;
                endIdx = 18534;
                break;
            case "Isaiah 44":
            case "이사야 44":
                beginIdx = 18535;
                endIdx = 18562;
                break;
            case "Isaiah 45":
            case "이사야 45":
                beginIdx = 18563;
                endIdx = 18587;
                break;
            case "Isaiah 46":
            case "이사야 46":
                beginIdx = 18588;
                endIdx = 18600;
                break;
            case "Isaiah 47":
            case "이사야 47":
                beginIdx = 18601;
                endIdx = 18615;
                break;
            case "Isaiah 48":
            case "이사야 48":
                beginIdx = 18616;
                endIdx = 18637;
                break;
            case "Isaiah 49":
            case "이사야 49":
                beginIdx = 18638;
                endIdx = 18663;
                break;
            case "Isaiah 50":
            case "이사야 50":
                beginIdx = 18664;
                endIdx = 18674;
                break;
            case "Isaiah 51":
            case "이사야 51":
                beginIdx = 18675;
                endIdx = 18697;
                break;
            case "Isaiah 52":
            case "이사야 52":
                beginIdx = 18698;
                endIdx = 18712;
                break;
            case "Isaiah 53":
            case "이사야 53":
                beginIdx = 18713;
                endIdx = 18724;
                break;
            case "Isaiah 54":
            case "이사야 54":
                beginIdx = 18725;
                endIdx = 18741;
                break;
            case "Isaiah 55":
            case "이사야 55":
                beginIdx = 18742;
                endIdx = 18754;
                break;
            case "Isaiah 56":
            case "이사야 56":
                beginIdx = 18755;
                endIdx = 18766;
                break;
            case "Isaiah 57":
            case "이사야 57":
                beginIdx = 18767;
                endIdx = 18787;
                break;
            case "Isaiah 58":
            case "이사야 58":
                beginIdx = 18788;
                endIdx = 18801;
                break;
            case "Isaiah 59":
            case "이사야 59":
                beginIdx = 18802;
                endIdx = 18822;
                break;
            case "Isaiah 60":
            case "이사야 60":
                beginIdx = 18823;
                endIdx = 18844;
                break;
            case "Isaiah 61":
            case "이사야 61":
                beginIdx = 18845;
                endIdx = 18855;
                break;
            case "Isaiah 62":
            case "이사야 62":
                beginIdx = 18856;
                endIdx = 18867;
                break;
            case "Isaiah 63":
            case "이사야 63":
                beginIdx = 18868;
                endIdx = 18886;
                break;
            case "Isaiah 64":
            case "이사야 64":
                beginIdx = 18887;
                endIdx = 18898;
                break;
            case "Isaiah 65":
            case "이사야 65":
                beginIdx = 18899;
                endIdx = 18923;
                break;
            case "Isaiah 66":
            case "이사야 66":
                beginIdx = 18924;
                endIdx = 18947;
                break;
        }
    }
    public void GetJeremiahVerse(String sheetTitle){
        switch (sheetTitle){
            case "Jeremiah 1":
            case "예레미야 1":
                beginIdx = 18948;
                endIdx = 18966;
                break;
            case "Jeremiah 2":
            case "예레미야 2":
                beginIdx = 18967;
                endIdx = 19003;
                break;
            case "Jeremiah 3":
            case "예레미야 3":
                beginIdx = 19004;
                endIdx = 19028;
                break;
            case "Jeremiah 4":
            case "예레미야 4":
                beginIdx = 19029;
                endIdx = 19059;
                break;
            case "Jeremiah 5":
            case "예레미야 5":
                beginIdx = 19060;
                endIdx = 19090;
                break;
            case "Jeremiah 6":
            case "예레미야 6":
                beginIdx = 19091;
                endIdx = 19120;
                break;
            case "Jeremiah 7":
            case "예레미야 7":
                beginIdx = 19121;
                endIdx = 19154;
                break;
            case "Jeremiah 8":
            case "예레미야 8":
                beginIdx = 19155;
                endIdx = 19176;
                break;
            case "Jeremiah 9":
            case "예레미야 9":
                beginIdx = 19177;
                endIdx = 19202;
                break;
            case "Jeremiah 10":
            case "예레미야 10":
                beginIdx = 19203;
                endIdx = 19227;
                break;
            case "Jeremiah 11":
            case "예레미야 11":
                beginIdx = 19228;
                endIdx = 19250;
                break;
            case "Jeremiah 12":
            case "예레미야 12":
                beginIdx = 19251;
                endIdx = 19267;
                break;
            case "Jeremiah 13":
            case "예레미야 13":
                beginIdx = 19268;
                endIdx = 19294;
                break;
            case "Jeremiah 14":
            case "예레미야 14":
                beginIdx = 19295;
                endIdx = 19316;
                break;
            case "Jeremiah 15":
            case "예레미야 15":
                beginIdx = 19317;
                endIdx = 19337;
                break;
            case "Jeremiah 16":
            case "예레미야 16":
                beginIdx = 19338;
                endIdx = 19358;
                break;
            case "Jeremiah 17":
            case "예레미야 17":
                beginIdx = 19359;
                endIdx = 19385;
                break;
            case "Jeremiah 18":
            case "예레미야 18":
                beginIdx = 19386;
                endIdx = 19408;
                break;
            case "Jeremiah 19":
            case "예레미야 19":
                beginIdx = 19409;
                endIdx = 19423;
                break;
            case "Jeremiah 20":
            case "예레미야 20":
                beginIdx = 19424;
                endIdx = 19441;
                break;
            case "Jeremiah 21":
            case "예레미야 21":
                beginIdx = 19442;
                endIdx = 19455;
                break;
            case "Jeremiah 22":
            case "예레미야 22":
                beginIdx = 19456;
                endIdx = 19485;
                break;
            case "Jeremiah 23":
            case "예레미야 23":
                beginIdx = 19486;
                endIdx = 19525;
                break;
            case "Jeremiah 24":
            case "예레미야 24":
                beginIdx = 19526;
                endIdx = 19535;
                break;
            case "Jeremiah 25":
            case "예레미야 25":
                beginIdx = 19536;
                endIdx = 19573;
                break;
            case "Jeremiah 26":
            case "예레미야 26":
                beginIdx = 19574;
                endIdx = 19597;
                break;
            case "Jeremiah 27":
            case "예레미야 27":
                beginIdx = 19598;
                endIdx = 19619;
                break;
            case "Jeremiah 28":
            case "예레미야 28":
                beginIdx = 19620;
                endIdx = 19636;
                break;
            case "Jeremiah 29":
            case "예레미야 29":
                beginIdx = 19637;
                endIdx = 19668;
                break;
            case "Jeremiah 30":
            case "예레미야 30":
                beginIdx = 19669;
                endIdx = 19692;
                break;
            case "Jeremiah 31":
            case "예레미야 31":
                beginIdx = 19693;
                endIdx = 19732;
                break;
            case "Jeremiah 32":
            case "예레미야 32":
                beginIdx = 19733;
                endIdx = 19776;
                break;
            case "Jeremiah 33":
            case "예레미야 33":
                beginIdx = 19777;
                endIdx = 19802;
                break;
            case "Jeremiah 34":
            case "예레미야 34":
                beginIdx = 19803;
                endIdx = 19824;
                break;
            case "Jeremiah 35":
            case "예레미야 35":
                beginIdx = 19825;
                endIdx = 19843;
                break;
            case "Jeremiah 36":
            case "예레미야 36":
                beginIdx = 19844;
                endIdx = 19875;
                break;
            case "Jeremiah 37":
            case "예레미야 37":
                beginIdx = 19876;
                endIdx = 19896;
                break;
            case "Jeremiah 38":
            case "예레미야 38":
                beginIdx = 19897;
                endIdx = 19924;
                break;
            case "Jeremiah 39":
            case "예레미야 39":
                beginIdx = 19925;
                endIdx = 19942;
                break;
            case "Jeremiah 40":
            case "예레미야 40":
                beginIdx = 19943;
                endIdx = 19958;
                break;
            case "Jeremiah 41":
            case "예레미야 41":
                beginIdx = 19959;
                endIdx = 19976;
                break;
            case "Jeremiah 42":
            case "예레미야 42":
                beginIdx = 19977;
                endIdx = 19998;
                break;
            case "Jeremiah 43":
            case "예레미야 43":
                beginIdx = 19999;
                endIdx = 20011;
                break;
            case "Jeremiah 44":
            case "예레미야 44":
                beginIdx = 20012;
                endIdx = 20041;
                break;
            case "Jeremiah 45":
            case "예레미야 45":
                beginIdx = 20042;
                endIdx = 20046;
                break;
            case "Jeremiah 46":
            case "예레미야 46":
                beginIdx = 20047;
                endIdx = 20074;
                break;
            case "Jeremiah 47":
            case "예레미야 47":
                beginIdx = 20075;
                endIdx = 20081;
                break;
            case "Jeremiah 48":
            case "예레미야 48":
                beginIdx = 20082;
                endIdx = 20128;
                break;
            case "Jeremiah 49":
            case "예레미야 49":
                beginIdx = 20129;
                endIdx = 20167;
                break;
            case "Jeremiah 50":
            case "예레미야 50":
                beginIdx = 20168;
                endIdx = 20213;
                break;
            case "Jeremiah 51":
            case "예레미야 51":
                beginIdx = 20214;
                endIdx = 20277;
                break;
            case "Jeremiah 52":
            case "예레미야 52":
                beginIdx = 20278;
                endIdx = 20311;
                break;
        }
    }
    public void GetLamentationsVerse(String sheetTitle){
        switch (sheetTitle){
            case "Lamentations 1":
            case "예레미야애가 1":
                beginIdx = 20312;
                endIdx = 20333;
                break;
            case "Lamentations 2":
            case "예레미야애가 2":
                beginIdx = 20334;
                endIdx = 20355;
                break;
            case "Lamentations 3":
            case "예레미야애가 3":
                beginIdx = 20356;
                endIdx = 20421;
                break;
            case "Lamentations 4":
            case "예레미야애가 4":
                beginIdx = 20422;
                endIdx = 20443;
                break;
            case "Lamentations 5":
            case "예레미야애가 5":
                beginIdx = 20444;
                endIdx = 20465;
                break;
        }
    }
    public void GetEzekielVerse(String sheetTitle){
        switch (sheetTitle){
            case "Ezekiel 1":
            case "에스겔 1":
                beginIdx = 20445;
                endIdx = 20493;
                break;
            case "Ezekiel 2":
            case "에스겔 2":
                beginIdx = 20494;
                endIdx = 20503;
                break;
            case "Ezekiel 3":
            case "에스겔 3":
                beginIdx = 20504;
                endIdx = 20530;
                break;
            case "Ezekiel 4":
            case "에스겔 4":
                beginIdx = 20531;
                endIdx = 20547;
                break;
            case "Ezekiel 5":
            case "에스겔 5":
                beginIdx = 20548;
                endIdx = 20564;
                break;
            case "Ezekiel 6":
            case "에스겔 6":
                beginIdx = 20565;
                endIdx = 20578;
                break;
            case "Ezekiel 7":
            case "에스겔 7":
                beginIdx = 20579;
                endIdx = 20605;
                break;
            case "Ezekiel 8":
            case "에스겔 8":
                beginIdx = 20606;
                endIdx = 20623;
                break;
            case "Ezekiel 9":
            case "에스겔 9":
                beginIdx = 20624;
                endIdx = 20634;
                break;
            case "Ezekiel 10":
            case "에스겔 10":
                beginIdx = 20635;
                endIdx = 20656;
                break;
            case "Ezekiel 11":
            case "에스겔 11":
                beginIdx = 20657;
                endIdx = 20681;
                break;
            case "Ezekiel 12":
            case "에스겔 12":
                beginIdx = 20682;
                endIdx = 20709;
                break;
            case "Ezekiel 13":
            case "에스겔 13":
                beginIdx = 20710;
                endIdx = 20732;
                break;
            case "Ezekiel 14":
            case "에스겔 14":
                beginIdx = 20733;
                endIdx = 20755;
                break;
            case "Ezekiel 15":
            case "에스겔 15":
                beginIdx = 20756;
                endIdx = 20763;
                break;
            case "Ezekiel 16":
            case "에스겔 16":
                beginIdx = 20764;
                endIdx = 20826;
                break;
            case "Ezekiel 17":
            case "에스겔 17":
                beginIdx = 20827;
                endIdx = 20850;
                break;
            case "Ezekiel 18":
            case "에스겔 18":
                beginIdx = 20851;
                endIdx = 20882;
                break;
            case "Ezekiel 19":
            case "에스겔 19":
                beginIdx = 20883;
                endIdx = 20896;
                break;
            case "Ezekiel 20":
            case "에스겔 20":
                beginIdx = 20897;
                endIdx = 20945;
                break;
            case "Ezekiel 21":
            case "에스겔 21":
                beginIdx = 20946;
                endIdx = 20977;
                break;
            case "Ezekiel 22":
            case "에스겔 22":
                beginIdx = 20978;
                endIdx = 21008;
                break;
            case "Ezekiel 23":
            case "에스겔 23":
                beginIdx = 21009;
                endIdx = 21057;
                break;
            case "Ezekiel 24":
            case "에스겔 24":
                beginIdx = 21058;
                endIdx = 21084;
                break;
            case "Ezekiel 25":
            case "에스겔 25":
                beginIdx = 21085;
                endIdx = 21101;
                break;
            case "Ezekiel 26":
            case "에스겔 26":
                beginIdx = 21102;
                endIdx = 21122;
                break;
            case "Ezekiel 27":
            case "에스겔 27":
                beginIdx = 21123;
                endIdx = 21158;
                break;
            case "Ezekiel 28":
            case "에스겔 28":
                beginIdx = 21159;
                endIdx = 21184;
                break;
            case "Ezekiel 29":
            case "에스겔 29":
                beginIdx = 21185;
                endIdx = 21205;
                break;
            case "Ezekiel 30":
            case "에스겔 30":
                beginIdx = 21206;
                endIdx = 21231;
                break;
            case "Ezekiel 31":
            case "에스겔 31":
                beginIdx = 21232;
                endIdx = 21249;
                break;
            case "Ezekiel 32":
            case "에스겔 32":
                beginIdx = 21250;
                endIdx = 21281;
                break;
            case "Ezekiel 33":
            case "에스겔 33":
                beginIdx = 21282;
                endIdx = 21314;
                break;
            case "Ezekiel 34":
            case "에스겔 34":
                beginIdx = 21315;
                endIdx = 21345;
                break;
            case "Ezekiel 35":
            case "에스겔 35":
                beginIdx = 21346;
                endIdx = 21360;
                break;
            case "Ezekiel 36":
            case "에스겔 36":
                beginIdx = 21361;
                endIdx = 21398;
                break;
            case "Ezekiel 37":
            case "에스겔 37":
                beginIdx = 21399;
                endIdx = 21426;
                break;
            case "Ezekiel 38":
            case "에스겔 38":
                beginIdx = 21427;
                endIdx = 21449;
                break;
            case "Ezekiel 39":
            case "에스겔 39":
                beginIdx = 21450;
                endIdx = 21478;
                break;
            case "Ezekiel 40":
            case "에스겔 40":
                beginIdx = 21479;
                endIdx = 21527;
                break;
            case "Ezekiel 41":
            case "에스겔 41":
                beginIdx = 21528;
                endIdx = 21553;
                break;
            case "Ezekiel 42":
            case "에스겔 42":
                beginIdx = 21554;
                endIdx = 21573;
                break;
            case "Ezekiel 43":
            case "에스겔 43":
                beginIdx = 21574;
                endIdx = 21600;
                break;
            case "Ezekiel 44":
            case "에스겔 44":
                beginIdx = 21601;
                endIdx = 21631;
                break;
            case "Ezekiel 45":
            case "에스겔 45":
                beginIdx = 21632;
                endIdx = 21656;
                break;
            case "Ezekiel 46":
            case "에스겔 46":
                beginIdx = 21657;
                endIdx = 21680;
                break;
            case "Ezekiel 47":
            case "에스겔 47":
                beginIdx = 21681;
                endIdx = 21703;
                break;
            case "Ezekiel 48":
            case "에스겔 48":
                beginIdx = 21704;
                endIdx = 21738;
                break;
        }
    }
    public void GetDanielVerse(String sheetTitle){
        switch (sheetTitle){
            case "Daniel 1":
            case "다니엘 1":
                beginIdx = 21739;
                endIdx = 21759;
                break;
            case "Daniel 2":
            case "다니엘 2":
                beginIdx = 21760;
                endIdx = 21808;
                break;
            case "Daniel 3":
            case "다니엘 3":
                beginIdx = 21809;
                endIdx = 21838;
                break;
            case "Daniel 4":
            case "다니엘 4":
                beginIdx = 21839;
                endIdx = 21875;
                break;
            case "Daniel 5":
            case "다니엘 5":
                beginIdx = 21876;
                endIdx = 21906;
                break;
            case "Daniel 6":
            case "다니엘 6":
                beginIdx = 21907;
                endIdx = 21934;
                break;
            case "Daniel 7":
            case "다니엘 7":
                beginIdx = 21935;
                endIdx = 21962;
                break;
            case "Daniel 8":
            case "다니엘 8":
                beginIdx = 21963;
                endIdx = 21989;
                break;
            case "Daniel 9":
            case "다니엘 9":
                beginIdx = 21990;
                endIdx = 22016;
                break;
            case "Daniel 10":
            case "다니엘 10":
                beginIdx = 22017;
                endIdx = 22037;
                break;
            case "Daniel 11":
            case "다니엘 11":
                beginIdx = 22038;
                endIdx = 22082;
                break;
            case "Daniel 12":
            case "다니엘 12":
                beginIdx = 22083;
                endIdx = 22095;
                break;
        }
    }
    public void GetHoseaVerse(String sheetTitle){
        switch (sheetTitle){
            case "Hosea 1":
            case "호세아 1":
                beginIdx = 22096;
                endIdx = 22106;
                break;
            case "Hosea 2":
            case "호세아 2":
                beginIdx = 22107;
                endIdx = 22129;
                break;
            case "Hosea 3":
            case "호세아 3":
                beginIdx = 22130;
                endIdx = 22134;
                break;
            case "Hosea 4":
            case "호세아 4":
                beginIdx = 22135;
                endIdx = 22153;
                break;
            case "Hosea 5":
            case "호세아 5":
                beginIdx = 22154;
                endIdx = 22168;
                break;
            case "Hosea 6":
            case "호세아 6":
                beginIdx = 22169;
                endIdx = 22179;
                break;
            case "Hosea 7":
            case "호세아 7":
                beginIdx = 22180;
                endIdx = 22195;
                break;
            case "Hosea 8":
            case "호세아 8":
                beginIdx = 22196;
                endIdx = 22209;
                break;
            case "Hosea 9":
            case "호세아 9":
                beginIdx = 22210;
                endIdx = 22226;
                break;
            case "Hosea 10":
            case "호세아 10":
                beginIdx = 22227;
                endIdx = 22241;
                break;
            case "Hosea 11":
            case "호세아 11":
                beginIdx = 22242;
                endIdx = 22253;
                break;
            case "Hosea 12":
            case "호세아 12":
                beginIdx = 22254;
                endIdx = 22267;
                break;
            case "Hosea 13":
            case "호세아 13":
                beginIdx = 22268;
                endIdx = 22283;
                break;
            case "Hosea 14":
            case "호세아 14":
                beginIdx = 22284;
                endIdx = 22292;
                break;
        }

    }
    public void GetJoelVerse(String sheetTitle){
        switch (sheetTitle){
            case "Joel 1":
            case "요엘 1":
                beginIdx = 22293;
                endIdx = 22312;
                break;
            case "Joel 2":
            case "요엘 2":
                beginIdx = 22313;
                endIdx = 22344;
                break;
            case "Joel 3":
            case "요엘 3":
                beginIdx = 22345;
                endIdx = 22365;
                break;
        }
    }
    public void GetAmosVerse(String sheetTitle){
        switch (sheetTitle){
            case "Amos 1":
            case "아모스 1":
                beginIdx = 22366;
                endIdx = 22380;
                break;
            case "Amos 2":
            case "아모스 2":
                beginIdx = 22381;
                endIdx = 22396;
                break;
            case "Amos 3":
            case "아모스 3":
                beginIdx = 22397;
                endIdx = 22411;
                break;
            case "Amos 4":
            case "아모스 4":
                beginIdx = 22412;
                endIdx = 22424;
                break;
            case "Amos 5":
            case "아모스 5":
                beginIdx = 22425;
                endIdx = 22451;
                break;
            case "Amos 6":
            case "아모스 6":
                beginIdx = 22452;
                endIdx = 22465;
                break;
            case "Amos 7":
            case "아모스 7":
                beginIdx = 22466;
                endIdx = 22482;
                break;
            case "Amos 8":
            case "아모스 8":
                beginIdx = 22483;
                endIdx = 22496;
                break;
            case "Amos 9":
            case "아모스 9":
                beginIdx = 22497;
                endIdx = 22511;
                break;
        }
    }
    public void GetObadiahVerse(String sheetTitle){
        switch (sheetTitle){
            case "Obadiah 1":
            case "오바댜 1":
                beginIdx = 22512;
                endIdx = 22532;
                break;
        }
    }
    public void GetJonahVerse(String sheetTitle){
        switch (sheetTitle){
            case "Jonah 1":
            case "요나 1":
                beginIdx = 22533;
                endIdx = 22549;
                break;
            case "Jonah 2":
            case "요나 2":
                beginIdx = 22550;
                endIdx = 22559;
                break;
            case "Jonah 3":
            case "요나 3":
                beginIdx = 22560;
                endIdx = 22569;
                break;
            case "Jonah 4":
            case "요나 4":
                beginIdx = 22570;
                endIdx = 22580;
                break;
        }
    }
    public void GetMicahVerse(String sheetTitle){
        switch (sheetTitle){
            case "Micah 1":
            case "미가 1":
                beginIdx = 22581;
                endIdx = 22596;
                break;
            case "Micah 2":
            case "미가 2":
                beginIdx = 22597;
                endIdx = 22609;
                break;
            case "Micah 3":
            case "미가 3":
                beginIdx = 22610;
                endIdx = 22621;
                break;
            case "Micah 4":
            case "미가 4":
                beginIdx = 22622;
                endIdx = 22634;
                break;
            case "Micah 5":
            case "미가 5":
                beginIdx = 22635;
                endIdx = 22649;
                break;
            case "Micah 6":
            case "미가 6":
                beginIdx = 22650;
                endIdx = 22665;
                break;
            case "Micah 7":
            case "미가 7":
                beginIdx = 22666;
                endIdx = 22685;
                break;
        }
    }
    public void GetNahumVerse(String sheetTitle){
        switch (sheetTitle){
            case "Nahum 1":
            case "나훔 1":
                beginIdx = 22686;
                endIdx = 22700;
                break;
            case "Nahum 2":
            case "나훔 2":
                beginIdx = 22701;
                endIdx = 22713;
                break;
            case "Nahum 3":
            case "나훔 3":
                beginIdx = 22714;
                endIdx = 22732;
                break;
        }
    }
    public void GetHabakkukVerse(String sheetTitle){
        switch (sheetTitle){
            case "Habakkuk 1":
            case "하박국 1":
                beginIdx = 22733;
                endIdx = 22749;
                break;
            case "Habakkuk 2":
            case "하박국 2":
                beginIdx = 22750;
                endIdx = 22769;
                break;
            case "Habakkuk 3":
            case "하박국 3":
                beginIdx = 22770;
                endIdx = 22788;
                break;
        }
    }
    public void GetZephaniahVerse(String sheetTitle){
        switch (sheetTitle){
            case "Zephaniah 1":
            case "스바냐 1":
                beginIdx = 22789;
                endIdx = 22806;
                break;
            case "Zephaniah 2":
            case "스바냐 2":
                beginIdx = 22807;
                endIdx = 22821;
                break;
            case "Zephaniah 3":
            case "스바냐 3":
                beginIdx = 22822;
                endIdx = 22841;
                break;
        }
    }
    public void GetHaggaiVerse(String sheetTitle){
        switch (sheetTitle){
            case "Haggai 1":
            case "학개 1":
                beginIdx = 22842;
                endIdx = 22856;
                break;
            case "Haggai 2":
            case "학개 2":
                beginIdx = 22857;
                endIdx = 22879;
                break;
        }
    }
    public void GetZechariahVerse(String sheetTitle){
        switch (sheetTitle){
            case "Zechariah 1":
            case "스가랴 1":
                beginIdx = 22880;
                endIdx = 22900;
                break;
            case "Zechariah 2":
            case "스가랴 2":
                beginIdx = 22901;
                endIdx = 22913;
                break;
            case "Zechariah 3":
            case "스가랴 3":
                beginIdx = 22914;
                endIdx = 22922;
                break;
            case "Zechariah 4":
            case "스가랴 4":
                beginIdx = 22923;
                endIdx = 22937;
                break;
            case "Zechariah 5":
            case "스가랴 5":
                beginIdx = 22938;
                endIdx = 22948;
                break;
            case "Zechariah 6":
            case "스가랴 6":
                beginIdx = 22949;
                endIdx = 22963;
                break;
            case "Zechariah 7":
            case "스가랴 7":
                beginIdx = 22964;
                endIdx = 22977;
                break;
            case "Zechariah 8":
            case "스가랴 8":
                beginIdx = 22978;
                endIdx = 23000;
                break;
            case "Zechariah 9":
            case "스가랴 9":
                beginIdx = 23001;
                endIdx = 23017;
                break;
            case "Zechariah 10":
            case "스가랴 10":
                beginIdx = 23018;
                endIdx = 23029;
                break;
            case "Zechariah 11":
            case "스가랴 11":
                beginIdx = 23030;
                endIdx = 23046;
                break;
            case "Zechariah 12":
            case "스가랴 12":
                beginIdx = 23047;
                endIdx = 23060;
                break;
            case "Zechariah 13":
            case "스가랴 13":
                beginIdx = 23061;
                endIdx = 23069;
                break;
            case "Zechariah 14":
            case "스가랴 14":
                beginIdx = 23070;
                endIdx = 23090;
                break;
        }
    }
    public void GetMalachiVerse(String sheetTitle){
        switch (sheetTitle){
            case "Malachi 1":
            case "말라기 1":
                beginIdx = 23091;
                endIdx = 23104;
                break;
            case "Malachi 2":
            case "말라기 2":
                beginIdx = 23105;
                endIdx = 23121;
                break;
            case "Malachi 3":
            case "말라기 3":
                beginIdx = 23122;
                endIdx = 23139;
                break;
            case "Malachi 4":
            case "말라기 4":
                beginIdx = 23140;
                endIdx = 23145;
                break;
        }
    }
    public void GetMatthewVerse(String sheetTitle){
        switch (sheetTitle){
            case "Matthew 1":
            case "마태복음 1":
                beginIdx = 23146;
                endIdx = 23170;
                break;
            case "Matthew 2":
            case "마태복음 2":
                beginIdx = 23171;
                endIdx = 23193;
                break;
            case "Matthew 3":
            case "마태복음 3":
                beginIdx = 23194;
                endIdx = 23210;
                break;
            case "Matthew 4":
            case "마태복음 4":
                beginIdx = 23211;
                endIdx = 23235;
                break;
            case "Matthew 5":
            case "마태복음 5":
                beginIdx = 23236;
                endIdx = 23283;
                break;
            case "Matthew 6":
            case "마태복음 6":
                beginIdx = 23284;
                endIdx = 23317;
                break;
            case "Matthew 7":
            case "마태복음 7":
                beginIdx = 23318;
                endIdx = 23346;
                break;
            case "Matthew 8":
            case "마태복음 8":
                beginIdx = 23347;
                endIdx = 23380;
                break;
            case "Matthew 9":
            case "마태복음 9":
                beginIdx = 23381;
                endIdx = 23418;
                break;
            case "Matthew 10":
            case "마태복음 10":
                beginIdx = 23419;
                endIdx = 23460;
                break;
            case "Matthew 11":
            case "마태복음 11":
                beginIdx = 23461;
                endIdx = 23490;
                break;
            case "Matthew 12":
            case "마태복음 12":
                beginIdx = 23491;
                endIdx = 23540;
                break;
            case "Matthew 13":
            case "마태복음 13":
                beginIdx = 23541;
                endIdx = 23598;
                break;
            case "Matthew 14":
            case "마태복음 14":
                beginIdx = 23599;
                endIdx = 23634;
                break;
            case "Matthew 15":
            case "마태복음 15":
                beginIdx = 23635;
                endIdx = 23673;
                break;
            case "Matthew 16":
            case "마태복음 16":
                beginIdx = 23674;
                endIdx = 23701;
                break;
            case "Matthew 17":
            case "마태복음 17":
                beginIdx = 23702;
                endIdx = 23728;
                break;
            case "Matthew 18":
            case "마태복음 18":
                beginIdx = 23729;
                endIdx = 23763;
                break;
            case "Matthew 19":
            case "마태복음 19":
                beginIdx = 23764;
                endIdx = 23793;
                break;
            case "Matthew 20":
            case "마태복음 20":
                beginIdx = 23794;
                endIdx = 23827;
                break;
            case "Matthew 21":
            case "마태복음 21":
                beginIdx = 23828;
                endIdx = 23873;
                break;
            case "Matthew 22":
            case "마태복음 22":
                beginIdx = 23874;
                endIdx = 23919;
                break;
            case "Matthew 23":
            case "마태복음 23":
                beginIdx = 23920;
                endIdx = 23958;
                break;
            case "Matthew 24":
            case "마태복음 24":
                beginIdx = 23959;
                endIdx = 24009;
                break;
            case "Matthew 25":
            case "마태복음 25":
                beginIdx = 24010;
                endIdx = 24055;
                break;
            case "Matthew 26":
            case "마태복음 26":
                beginIdx = 24056;
                endIdx = 24130;
                break;
            case "Matthew 27":
            case "마태복음 27":
                beginIdx = 24131;
                endIdx = 24196;
                break;
            case "Matthew 28":
            case "마태복음 28":
                beginIdx = 24197;
                endIdx = 24216;
                break;
        }
    }
    public void GetMarkVerse(String sheetTitle){
        switch (sheetTitle){
            case "Mark 1":
            case "마가복음 1":
                beginIdx = 24217;
                endIdx = 24261;
                break;
            case "Mark 2":
            case "마가복음 2":
                beginIdx = 24262;
                endIdx = 24289;
                break;
            case "Mark 3":
            case "마가복음 3":
                beginIdx = 24290;
                endIdx = 24324;
                break;
            case "Mark 4":
            case "마가복음 4":
                beginIdx = 24325;
                endIdx = 24365;
                break;
            case "Mark 5":
            case "마가복음 5":
                beginIdx = 24366;
                endIdx = 24408;
                break;
            case "Mark 6":
            case "마가복음 6":
                beginIdx = 24409;
                endIdx = 24464;
                break;
            case "Mark 7":
            case "마가복음 7":
                beginIdx = 24465;
                endIdx = 24501;
                break;
            case "Mark 8":
            case "마가복음 8":
                beginIdx = 24502;
                endIdx = 24539;
                break;
            case "Mark 9":
            case "마가복음 9":
                beginIdx = 24540;
                endIdx = 24589;
                break;
            case "Mark 10":
            case "마가복음 10":
                beginIdx = 24590;
                endIdx = 24641;
                break;
            case "Mark 11":
            case "마가복음 11":
                beginIdx = 24642;
                endIdx = 24674;
                break;
            case "Mark 12":
            case "마가복음 12":
                beginIdx = 24675;
                endIdx = 24718;
                break;
            case "Mark 13":
            case "마가복음 13":
                beginIdx = 24719;
                endIdx = 24755;
                break;
            case "Mark 14":
            case "마가복음 14":
                beginIdx = 24756;
                endIdx = 24827;
                break;
            case "Mark 15":
            case "마가복음 15":
                beginIdx = 24828;
                endIdx = 24874;
                break;
            case "Mark 16":
            case "마가복음 16":
                beginIdx = 24875;
                endIdx = 24894;
                break;
        }
    }
    public void GetLukeVerse(String sheetTitle){
        switch (sheetTitle){
            case "Luke 1":
            case "누가복음 1":
                beginIdx = 24895;
                endIdx = 24974;
                break;
            case "Luke 2":
            case "누가복음 2":
                beginIdx = 24975;
                endIdx = 25026;
                break;
            case "Luke 3":
            case "누가복음 3":
                beginIdx = 25027;
                endIdx = 25064;
                break;
            case "Luke 4":
            case "누가복음 4":
                beginIdx = 25065;
                endIdx = 25108;
                break;
            case "Luke 5":
            case "누가복음 5":
                beginIdx = 25109;
                endIdx = 25147;
                break;
            case "Luke 6":
            case "누가복음 6":
                beginIdx = 25148;
                endIdx = 25196;
                break;
            case "Luke 7":
            case "누가복음 7":
                beginIdx = 25197;
                endIdx = 25246;
                break;
            case "Luke 8":
            case "누가복음 8":
                beginIdx = 25247;
                endIdx = 25302;
                break;
            case "Luke 9":
            case "누가복음 9":
                beginIdx = 25303;
                endIdx = 25364;
                break;
            case "Luke 10":
            case "누가복음 10":
                beginIdx = 25365;
                endIdx = 25406;
                break;
            case "Luke 11":
            case "누가복음 11":
                beginIdx = 25407;
                endIdx = 25460;
                break;
            case "Luke 12":
            case "누가복음 12":
                beginIdx = 25461;
                endIdx = 25519;
                break;
            case "Luke 13":
            case "누가복음 13":
                beginIdx = 25520;
                endIdx = 25554;
                break;
            case "Luke 14":
            case "누가복음 14":
                beginIdx = 25555;
                endIdx = 25589;
                break;
            case "Luke 15":
            case "누가복음 15":
                beginIdx = 25590;
                endIdx = 25621;
                break;
            case "Luke 16":
            case "누가복음 16":
                beginIdx = 25622;
                endIdx = 25652;
                break;
            case "Luke 17":
            case "누가복음 17":
                beginIdx = 25653;
                endIdx = 25689;
                break;
            case "Luke 18":
            case "누가복음 18":
                beginIdx = 25690;
                endIdx = 25732;
                break;
            case "Luke 19":
            case "누가복음 19":
                beginIdx = 25733;
                endIdx = 25780;
                break;
            case "Luke 20":
            case "누가복음 20":
                beginIdx = 25781;
                endIdx = 25827;
                break;
            case "Luke 21":
            case "누가복음 21":
                beginIdx = 25828;
                endIdx = 25865;
                break;
            case "Luke 22":
            case "누가복음 22":
                beginIdx = 25866;
                endIdx = 25936;
                break;
            case "Luke 23":
            case "누가복음 23":
                beginIdx = 25937;
                endIdx = 25992;
                break;
            case "Luke 24":
            case "누가복음 24":
                beginIdx = 25993;
                endIdx = 26045;
                break;
        }
    }
    public void GetJohnVerse(String sheetTitle){
        switch (sheetTitle){
            case "John 1":
            case "요한복음 1":
                beginIdx = 26046;
                endIdx =26096;
                break;
            case "John 2":
            case "요한복음 2":
                beginIdx = 26097;
                endIdx =26121;
                break;
            case "John 3":
            case "요한복음 3":
                beginIdx = 26122;
                endIdx =26157;
                break;
            case "John 4":
            case "요한복음 4":
                beginIdx = 26158;
                endIdx =26211;
                break;
            case "John 5":
            case "요한복음 5":
                beginIdx = 26212;
                endIdx =26258;
                break;
            case "John 6":
            case "요한복음 6":
                beginIdx = 26259;
                endIdx =26329;
                break;
            case "John 7":
            case "요한복음 7":
                beginIdx = 26330;
                endIdx =26382;
                break;
            case "John 8":
            case "요한복음 8":
                beginIdx = 26383;
                endIdx =26441;
                break;
            case "John 9":
            case "요한복음 9":
                beginIdx = 26442;
                endIdx =26482;
                break;
            case "John 10":
            case "요한복음 10":
                beginIdx = 26483;
                endIdx =26524;
                break;
            case "John 11":
            case "요한복음 11":
                beginIdx = 26525;
                endIdx =26581;
                break;
            case "John 12":
            case "요한복음 12":
                beginIdx = 26582;
                endIdx =26631;
                break;
            case "John 13":
            case "요한복음 13":
                beginIdx = 26632;
                endIdx =26669;
                break;
            case "John 14":
            case "요한복음 14":
                beginIdx = 26670;
                endIdx =26700;
                break;
            case "John 15":
            case "요한복음 15":
                beginIdx = 26701;
                endIdx =26727;
                break;
            case "John 16":
            case "요한복음 16":
                beginIdx = 26728;
                endIdx =26760;
                break;
            case "John 17":
            case "요한복음 17":
                beginIdx = 26761;
                endIdx =26786;
                break;
            case "John 18":
            case "요한복음 18":
                beginIdx = 26787;
                endIdx =26826;
                break;
            case "John 19":
            case "요한복음 19":
                beginIdx = 26827;
                endIdx =26868;
                break;
            case "John 20":
            case "요한복음 20":
                beginIdx = 26869;
                endIdx =26899;
                break;
            case "John 21":
            case "요한복음 21":
                beginIdx = 26900;
                endIdx =26924;
                break;
        }
    }
    public void GetActsVerse(String sheetTitle){
        switch (sheetTitle){
            case "Acts 1":
            case "사도행전 1":
                beginIdx = 26925;
                endIdx = 26950;
                break;
            case "Acts 2":
            case "사도행전 2":
                beginIdx = 26951;
                endIdx = 26997;
                break;
            case "Acts 3":
            case "사도행전 3":
                beginIdx = 26998;
                endIdx = 27023;
                break;
            case "Acts 4":
            case "사도행전 4":
                beginIdx = 27024;
                endIdx = 27060;
                break;
            case "Acts 5":
            case "사도행전 5":
                beginIdx = 27061;
                endIdx = 27102;
                break;
            case "Acts 6":
            case "사도행전 6":
                beginIdx = 27103;
                endIdx = 27117;
                break;
            case "Acts 7":
            case "사도행전 7":
                beginIdx = 27118;
                endIdx = 27177;
                break;
            case "Acts 8":
            case "사도행전 8":
                beginIdx = 27178;
                endIdx = 27217;
                break;
            case "Acts 9":
            case "사도행전 9":
                beginIdx = 27218;
                endIdx = 27260;
                break;
            case "Acts 10":
            case "사도행전 10":
                beginIdx = 27261;
                endIdx = 27308;
                break;
            case "Acts 11":
            case "사도행전 11":
                beginIdx = 27309;
                endIdx = 27338;
                break;
            case "Acts 12":
            case "사도행전 12":
                beginIdx = 27339;
                endIdx = 27363;
                break;
            case "Acts 13":
            case "사도행전 13":
                beginIdx = 27364;
                endIdx = 27415;
                break;
            case "Acts 14":
            case "사도행전 14":
                beginIdx = 27416;
                endIdx = 27443;
                break;
            case "Acts 15":
            case "사도행전 15":
                beginIdx = 27444;
                endIdx = 27484;
                break;
            case "Acts 16":
            case "사도행전 16":
                beginIdx = 27485;
                endIdx = 27524;
                break;
            case "Acts 17":
            case "사도행전 17":
                beginIdx = 27525;
                endIdx = 27558;
                break;
            case "Acts 18":
            case "사도행전 18":
                beginIdx = 27559;
                endIdx = 27586;
                break;
            case "Acts 19":
            case "사도행전 19":
                beginIdx = 27587;
                endIdx = 27627;
                break;
            case "Acts 20":
            case "사도행전 20":
                beginIdx = 27628;
                endIdx = 27665;
                break;
            case "Acts 21":
            case "사도행전 21":
                beginIdx = 27666;
                endIdx = 27705;
                break;
            case "Acts 22":
            case "사도행전 22":
                beginIdx = 27706;
                endIdx = 27735;
                break;
            case "Acts 23":
            case "사도행전 23":
                beginIdx = 27736;
                endIdx = 27770;
                break;
            case "Acts 24":
            case "사도행전 24":
                beginIdx = 27771;
                endIdx = 27797;
                break;
            case "Acts 25":
            case "사도행전 25":
                beginIdx = 27798;
                endIdx = 27824;
                break;
            case "Acts 26":
            case "사도행전 26":
                beginIdx = 27825;
                endIdx = 27856;
                break;
            case "Acts 27":
            case "사도행전 27":
                beginIdx = 27857;
                endIdx = 27900;
                break;
            case "Acts 28":
            case "사도행전 28":
                beginIdx = 27901;
                endIdx = 27931;
                break;
        }
    }
    public void GetRomansVerse(String sheetTitle){
        switch (sheetTitle){
            case "Romans 1":
            case "로마서 1":
                beginIdx = 27932;
                endIdx = 27963;
                break;
            case "Romans 2":
            case "로마서 2":
                beginIdx = 27964;
                endIdx = 27992;
                break;
            case "Romans 3":
            case "로마서 3":
                beginIdx = 27993;
                endIdx = 28023;
                break;
            case "Romans 4":
            case "로마서 4":
                beginIdx = 28024;
                endIdx = 28048;
                break;
            case "Romans 5":
            case "로마서 5":
                beginIdx = 28049;
                endIdx = 28069;
                break;
            case "Romans 6":
            case "로마서 6":
                beginIdx = 28070;
                endIdx = 28092;
                break;
            case "Romans 7":
            case "로마서 7":
                beginIdx = 28093;
                endIdx = 28117;
                break;
            case "Romans 8":
            case "로마서 8":
                beginIdx = 28118;
                endIdx = 28156;
                break;
            case "Romans 9":
            case "로마서 9":
                beginIdx = 28157;
                endIdx = 28189;
                break;
            case "Romans 10":
            case "로마서 10":
                beginIdx = 28190;
                endIdx = 28210;
                break;
            case "Romans 11":
            case "로마서 11":
                beginIdx = 28211;
                endIdx = 28246;
                break;
            case "Romans 12":
            case "로마서 12":
                beginIdx = 28247;
                endIdx = 28267;
                break;
            case "Romans 13":
            case "로마서 13":
                beginIdx = 28268;
                endIdx = 28281;
                break;
            case "Romans 14":
            case "로마서 14":
                beginIdx = 28282;
                endIdx = 28304;
                break;
            case "Romans 15":
            case "로마서 15":
                beginIdx = 28305;
                endIdx = 28337;
                break;
            case "Romans 16":
            case "로마서 16":
                beginIdx = 28338;
                endIdx = 28364;
                break;
        }
    }
    public void GetCorinthians1Verse(String sheetTitle){
        switch (sheetTitle){
            case "1 Corinthians 1":
            case "고린도전서 1":
                beginIdx = 28365;
                endIdx = 28395;
                break;
            case "1 Corinthians 2":
            case "고린도전서 2":
                beginIdx = 28396;
                endIdx = 28411;
                break;
            case "1 Corinthians 3":
            case "고린도전서 3":
                beginIdx = 28412;
                endIdx = 28434;
                break;
            case "1 Corinthians 4":
            case "고린도전서 4":
                beginIdx = 28435;
                endIdx = 28455;
                break;
            case "1 Corinthians 5":
            case "고린도전서 5":
                beginIdx = 28456;
                endIdx = 28468;
                break;
            case "1 Corinthians 6":
            case "고린도전서 6":
                beginIdx = 28469;
                endIdx = 28488;
                break;
            case "1 Corinthians 7":
            case "고린도전서 7":
                beginIdx = 28489;
                endIdx = 28528;
                break;
            case "1 Corinthians 8":
            case "고린도전서 8":
                beginIdx = 28529;
                endIdx = 28541;
                break;
            case "1 Corinthians 9":
            case "고린도전서 9":
                beginIdx = 28542;
                endIdx = 28568;
                break;
            case "1 Corinthians 10":
            case "고린도전서 10":
                beginIdx = 28569;
                endIdx = 28601;
                break;
            case "1 Corinthians 11":
            case "고린도전서 11":
                beginIdx = 28602;
                endIdx = 28635;
                break;
            case "1 Corinthians 12":
            case "고린도전서 12":
                beginIdx = 28636;
                endIdx = 28666;
                break;
            case "1 Corinthians 13":
            case "고린도전서 13":
                beginIdx = 28667;
                endIdx = 28679;
                break;
            case "1 Corinthians 14":
            case "고린도전서 14":
                beginIdx = 28680;
                endIdx = 28719;
                break;
            case "1 Corinthians 15":
            case "고린도전서 15":
                beginIdx = 28720;
                endIdx = 28777;
                break;
            case "1 Corinthians 16":
            case "고린도전서 16":
                beginIdx = 28778;
                endIdx = 28801;
                break;
        }
    }
    public void GetCorinthians2Verse(String sheetTitle){
        switch (sheetTitle) {
            case "2 Corinthians 1":
            case "고린도후서 1":
                beginIdx = 28802;
                endIdx = 28825;
                break;
            case "2 Corinthians 2":
            case "고린도후서 2":
                beginIdx = 28826;
                endIdx = 28842;
                break;
            case "2 Corinthians 3":
            case "고린도후서 3":
                beginIdx = 28843;
                endIdx = 28860;
                break;
            case "2 Corinthians 4":
            case "고린도후서 4":
                beginIdx = 28861;
                endIdx = 28878;
                break;
            case "2 Corinthians 5":
            case "고린도후서 5":
                beginIdx = 28879;
                endIdx = 28899;
                break;
            case "2 Corinthians 6":
            case "고린도후서 6":
                beginIdx = 28900;
                endIdx = 28917;
                break;
            case "2 Corinthians 7":
            case "고린도후서 7":
                beginIdx = 28918;
                endIdx = 28933;
                break;
            case "2 Corinthians 8":
            case "고린도후서 8":
                beginIdx = 28934;
                endIdx = 28957;
                break;
            case "2 Corinthians 9":
            case "고린도후서 9":
                beginIdx = 28958;
                endIdx = 28972;
                break;
            case "2 Corinthians 10":
            case "고린도후서 10":
                beginIdx = 28973;
                endIdx = 28990;
                break;
            case "2 Corinthians 11":
            case "고린도후서 11":
                beginIdx = 28991;
                endIdx = 29023;
                break;
            case "2 Corinthians 12":
            case "고린도후서 12":
                beginIdx = 29024;
                endIdx = 29044;
                break;
            case "2 Corinthians 13":
            case "고린도후서 13":
                beginIdx = 29045;
                endIdx = 29058;
                break;
        }
    }
    public void GetGalatiansVerse(String sheetTitle){
        switch (sheetTitle){
            case "Galatians 1":
            case "갈라디아서 1":
                beginIdx = 29059;
                endIdx = 29082;
                break;
            case "Galatians 2":
            case "갈라디아서 2":
                beginIdx = 29083;
                endIdx = 29103;
                break;
            case "Galatians 3":
            case "갈라디아서 3":
                beginIdx = 29104;
                endIdx = 29132;
                break;
            case "Galatians 4":
            case "갈라디아서 4":
                beginIdx = 29133;
                endIdx = 29163;
                break;
            case "Galatians 5":
            case "갈라디아서 5":
                beginIdx = 29164;
                endIdx = 29189;
                break;
            case "Galatians 6":
            case "갈라디아서 6":
                beginIdx = 29190;
                endIdx = 29207;
                break;
        }
    }
    public void GetEphesiansVerse(String sheetTitle){
        switch (sheetTitle){
            case "Ephesians 1":
            case "에베소서 1":
                beginIdx = 29191;
                endIdx = 29230;
                break;
            case "Ephesians 2":
            case "에베소서 2":
                beginIdx = 29231;
                endIdx = 29252;
                break;
            case "Ephesians 3":
            case "에베소서 3":
                beginIdx = 29253;
                endIdx = 29273;
                break;
            case "Ephesians 4":
            case "에베소서 4":
                beginIdx = 29274;
                endIdx = 29305;
                break;
            case "Ephesians 5":
            case "에베소서 5":
                beginIdx = 29306;
                endIdx = 29338;
                break;
            case "Ephesians 6":
            case "에베소서 6":
                beginIdx = 29339;
                endIdx = 29362;
                break;
        }
    }
    public void GetPhilippiansVerse(String sheetTitle){
        switch (sheetTitle){
            case "Philippians 1":
            case "빌립보서 1":
                beginIdx = 29363;
                endIdx = 29392;
                break;
            case "Philippians 2":
            case "빌립보서 2":
                beginIdx = 29393;
                endIdx = 29422;
                break;
            case "Philippians 3":
            case "빌립보서 3":
                beginIdx = 29423;
                endIdx = 29443;
                break;
            case "Philippians 4":
            case "빌립보서 4":
                beginIdx = 29444;
                endIdx = 29466;
                break;
        }
    }
    public void GetColossiansVerse(String sheetTitle){
        switch (sheetTitle){
            case "Colossians 1":
            case "골로새서 1":
                beginIdx = 29467;
                endIdx = 29495;
                break;
            case "Colossians 2":
            case "골로새서 2":
                beginIdx = 29496;
                endIdx = 29518;
                break;
            case "Colossians 3":
            case "골로새서 3":
                beginIdx = 29519;
                endIdx = 29543;
                break;
            case "Colossians 4":
            case "골로새서 4":
                beginIdx = 29544;
                endIdx = 29560;
                break;
        }
    }
    public void GetThessalonians1Verse(String sheetTitle){
        switch (sheetTitle){
            case "1 Thessalonians 1":
            case "데살로니가전서 1":
                beginIdx = 29561;
                endIdx = 29570;
                break;
            case "1 Thessalonians 2":
            case "데살로니가전서 2":
                beginIdx = 29571;
                endIdx = 29590;
                break;
            case "1 Thessalonians 3":
            case "데살로니가전서 3":
                beginIdx = 29591;
                endIdx = 29603;
                break;
            case "1 Thessalonians 4":
            case "데살로니가전서 4":
                beginIdx = 29604;
                endIdx = 29621;
                break;
            case "1 Thessalonians 5":
            case "데살로니가전서 5":
                beginIdx = 29622;
                endIdx = 29649;
                break;
        }
    }
    public void GetThessalonians2Verse(String sheetTitle){
        switch (sheetTitle){
            case "2 Thessalonians 1":
            case "데살로니가후서 1":
                beginIdx = 29650;
                endIdx = 29661;
                break;
            case "2 Thessalonians 2":
            case "데살로니가후서 2":
                beginIdx = 29662;
                endIdx = 29678;
                break;
            case "2 Thessalonians 3":
            case "데살로니가후서 3":
                beginIdx = 29679;
                endIdx = 29696;
                break;
        }
    }
    public void GetTimothy1Verse(String sheetTitle){
        switch (sheetTitle){
            case "1 Timothy 1":
            case "디모데전서 1":
                beginIdx = 29697;
                endIdx = 29716;
                break;
            case "1 Timothy 2":
            case "디모데전서 2":
                beginIdx = 29717;
                endIdx = 29731;
                break;
            case "1 Timothy 3":
            case "디모데전서 3":
                beginIdx = 29732;
                endIdx = 29747;
                break;
            case "1 Timothy 4":
            case "디모데전서 4":
                beginIdx = 29748;
                endIdx = 29763;
                break;
            case "1 Timothy 5":
            case "디모데전서 5":
                beginIdx = 29764;
                endIdx = 29788;
                break;
            case "1 Timothy 6":
            case "디모데전서 6":
                beginIdx = 29789;
                endIdx = 29809;
                break;
        }
    }
    public void GetTimothy2Verse(String sheetTitle){
        switch (sheetTitle){
            case "2 Timothy 1":
            case "디모데후서 1":
                beginIdx = 29810;
                endIdx = 29827;
                break;
            case "2 Timothy 2":
            case "디모데후서 2":
                beginIdx = 29828;
                endIdx = 29853;
                break;
            case "2 Timothy 3":
            case "디모데후서 3":
                beginIdx = 29854;
                endIdx = 29870;
                break;
            case "2 Timothy 4":
            case "디모데후서 4":
                beginIdx = 29871;
                endIdx = 29892;
                break;
        }
    }
    public void GetTitusVerse(String sheetTitle){
        switch (sheetTitle){
            case "Titus 1":
            case "디도서 1":
                beginIdx = 29893;
                endIdx = 29908;
                break;
            case "Titus 2":
            case "디도서 2":
                beginIdx = 29909;
                endIdx = 29923;
                break;
            case "Titus 3":
            case "디도서 3":
                beginIdx = 29924;
                endIdx = 29938;
                break;
        }
    }
    public void GetPhilemonVerse(String sheetTitle){
        beginIdx = 29939;
        endIdx = 29963;
    }
    public void GetHebrewsVerse(String sheetTitle){
        switch (sheetTitle){
            case "Hebrews 1":
            case "히브리서 1":
                beginIdx = 29964;
                endIdx = 29977;
                break;
            case "Hebrews 2":
            case "히브리서 2":
                beginIdx = 29978;
                endIdx = 29995;
                break;
            case "Hebrews 3":
            case "히브리서 3":
                beginIdx = 29996;
                endIdx = 30014;
                break;
            case "Hebrews 4":
            case "히브리서 4":
                beginIdx = 30015;
                endIdx = 30030;
                break;
            case "Hebrews 5":
            case "히브리서 5":
                beginIdx = 30031;
                endIdx = 30044;
                break;
            case "Hebrews 6":
            case "히브리서 6":
                beginIdx = 30045;
                endIdx = 30064;
                break;
            case "Hebrews 7":
            case "히브리서 7":
                beginIdx = 30065;
                endIdx = 30092;
                break;
            case "Hebrews 8":
            case "히브리서 8":
                beginIdx = 30093;
                endIdx = 30105;
                break;
            case "Hebrews 9":
            case "히브리서 9":
                beginIdx = 30106;
                endIdx = 30133;
                break;
            case "Hebrews 10":
            case "히브리서 10":
                beginIdx = 30134;
                endIdx = 30172;
                break;
            case "Hebrews 11":
            case "히브리서 11":
                beginIdx = 30173;
                endIdx = 30212;
                break;
            case "Hebrews 12":
            case "히브리서 12":
                beginIdx = 30213;
                endIdx = 30241;
                break;
            case "Hebrews 13":
            case "히브리서 13":
                beginIdx = 30242;
                endIdx = 30266;
                break;
        }
    }
    public void GetJamesVerse(String sheetTitle){
        switch (sheetTitle){
            case "James 1":
            case "야고보서 1":
                beginIdx = 30267;
                endIdx = 30293;
                break;
            case "James 2":
            case "야고보서 2":
                beginIdx = 30294;
                endIdx = 30319;
                break;
            case "James 3":
            case "야고보서 3":
                beginIdx = 30320;
                endIdx = 30337;
                break;
            case "James 4":
            case "야고보서 4":
                beginIdx = 30338;
                endIdx = 30354;
                break;
            case "James 5":
            case "야고보서 5":
                beginIdx = 30355;
                endIdx = 30374;
                break;

        }
    }
    public void GetPeter1Verse(String sheetTitle){
        switch (sheetTitle){
            case "1 Peter 1":
            case "베드로전서 1":
                beginIdx = 30375;
                endIdx = 30399;
                break;
            case "1 Peter 2":
            case "베드로전서 2":
                beginIdx = 30400;
                endIdx = 30424;
                break;
            case "1 Peter 3":
            case "베드로전서 3":
                beginIdx = 30425;
                endIdx = 30446;
                break;
            case "1 Peter 4":
            case "베드로전서 4":
                beginIdx = 30447;
                endIdx = 30465;
                break;
            case "1 Peter 5":
            case "베드로전서 5":
                beginIdx = 30466;
                endIdx = 30479;
                break;
        }
    }
    public void GetPeter2Verse(String sheetTitle){
        switch (sheetTitle){
            case "2 Peter 1":
            case "베드로후서 1":
                beginIdx = 30480;
                endIdx = 30500;
                break;
            case "2 Peter 2":
            case "베드로후서 2":
                beginIdx = 30501;
                endIdx = 30522;
                break;
            case "2 Peter 3":
            case "베드로후서 3":
                beginIdx = 30523;
                endIdx = 30540;
                break;
        }
    }
    public void GetJohn1Verse(String sheetTitle){
        switch (sheetTitle){
            case "1 John 1":
            case "요한일서 1":
                beginIdx = 30541;
                endIdx = 30550;
                break;
            case "1 John 2":
            case "요한일서 2":
                beginIdx = 30551;
                endIdx = 30579;
                break;
            case "1 John 3":
            case "요한일서 3":
                beginIdx = 30580;
                endIdx = 30603;
                break;
            case "1 John 4":
            case "요한일서 4":
                beginIdx = 30604;
                endIdx = 30624;
                break;
            case "1 John 5":
            case "요한일서 5":
                beginIdx = 30625;
                endIdx = 30645;
                break;
        }
    }
    public void GetJohn2Verse(String sheetTitle){
        beginIdx = 30646;
        endIdx = 30658;
    }
    public void GetJohn3Verse(String sheetTitle){
        beginIdx = 30659;
        endIdx = 30673;
    }
    public void GetJudeVerse(String sheetTitle){
        beginIdx = 30674;
        endIdx = 30698;
    }
    public void GetRevelationVerse(String sheetTitle){
        switch (sheetTitle){
            case "Revelation 1":
            case "요한계시록 1":
                beginIdx = 30699;
                endIdx = 30718;
                break;
            case "Revelation 2":
            case "요한계시록 2":
                beginIdx = 30719;
                endIdx = 30747;
                break;
            case "Revelation 3":
            case "요한계시록 3":
                beginIdx = 30748;
                endIdx = 30769;
                break;
            case "Revelation 4":
            case "요한계시록 4":
                beginIdx = 30770;
                endIdx = 30780;
                break;
            case "Revelation 5":
            case "요한계시록 5":
                beginIdx = 30781;
                endIdx = 30794;
                break;
            case "Revelation 6":
            case "요한계시록 6":
                beginIdx = 30795;
                endIdx = 30811;
                break;
            case "Revelation 7":
            case "요한계시록 7":
                beginIdx = 30812;
                endIdx = 30828;
                break;
            case "Revelation 8":
            case "요한계시록 8":
                beginIdx = 30829;
                endIdx = 30841;
                break;
            case "Revelation 9":
            case "요한계시록 9":
                beginIdx = 30842;
                endIdx = 30862;
                break;
            case "Revelation 10":
            case "요한계시록 10":
                beginIdx = 30863;
                endIdx = 30873;
                break;
            case "Revelation 11":
            case "요한계시록 11":
                beginIdx = 30874;
                endIdx = 30892;
                break;
            case "Revelation 12":
            case "요한계시록 12":
                beginIdx = 30893;
                endIdx = 30909;
                break;
            case "Revelation 13":
            case "요한계시록 13":
                beginIdx = 30910;
                endIdx = 30927;
                break;
            case "Revelation 14":
            case "요한계시록 14":
                beginIdx = 30928;
                endIdx = 30947;
                break;
            case "Revelation 15":
            case "요한계시록 15":
                beginIdx = 30948;
                endIdx = 30955;
                break;
            case "Revelation 16":
            case "요한계시록 16":
                beginIdx = 30956;
                endIdx = 30976;
                break;
            case "Revelation 17":
            case "요한계시록 17":
                beginIdx = 30977;
                endIdx = 30994;
                break;
            case "Revelation 18":
            case "요한계시록 18":
                beginIdx = 30995;
                endIdx = 31018;
                break;
            case "Revelation 19":
            case "요한계시록 19":
                beginIdx = 31019;
                endIdx = 31039;
                break;
            case "Revelation 20":
            case "요한계시록 20":
                beginIdx = 31040;
                endIdx = 31054;
                break;
            case "Revelation 21":
            case "요한계시록 21":
                beginIdx = 31055;
                endIdx = 31081;
                break;
            case "Revelation 22":
            case "요한계시록 22":
                beginIdx = 31082;
                endIdx = 31102;
                break;
        }
    }
}
