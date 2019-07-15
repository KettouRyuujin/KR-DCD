package dcdmod.Actions;

import dcdmod.Patches.AnimationLoader;

public class FaizAnimationAction {
    public static String FAR_ATLAS = "img/char/DCD_Animational/FAR/FAR.atlas";
    public static String AXEL5_ATLAS = "img/char/DCD_Animational/faiz_Axel/Axel_CrimsonSmash.atlas";
    public static String AXEL6_ATLAS = "img/char/DCD_Animational/faiz_Axel/Axel_PunchingUnit.atlas";
    public static String AXEL_FAR_JSON1 = "img/char/DCD_Animational/faiz_Axel/Axel_CrimsonSmash_1A.json";
    public static String AXEL_FAR_JSON2 = "img/char/DCD_Animational/faiz_Axel/Axel_CrimsonSmash_2A.json";
    public static String AXEL_FAR_JSON3 = "img/char/DCD_Animational/faiz_Axel/Axel_CrimsonSmash_3A.json";
    public static String AXEL_FAR_JSON4 = "img/char/DCD_Animational/faiz_Axel/Axel_PunchingUnit.json";
    public static AnimationLoader FAR_faiz = new AnimationLoader("img/char/DCD_Animational/FAR/FAR.atlas", "img/char/DCD_Animational/FAR/FAR_FAR_faiz.json",  1.0f);
    public static AnimationLoader faiz_FAR = new AnimationLoader("img/char/DCD_Animational/faiz/faiz_FAR3.atlas", "img/char/DCD_Animational/faiz/faiz_FAR3.json",  0.8f);
    public static AnimationLoader faiz_FAR2 = new AnimationLoader("img/char/DCD_Animational/faiz/faiz_FAR2.atlas", "img/char/DCD_Animational/faiz/faiz_FAR2.json",  0.8f);
    public static AnimationLoader faiz_punchingunit = new AnimationLoader("img/char/DCD_Animational/faiz/faiz_punchingunit.atlas", "img/char/DCD_Animational/faiz/faiz_punchingunit.json",  0.8f);
    public static AnimationLoader faiz_sparklecut = new AnimationLoader("img/char/DCD_Animational/faiz/faiz_sparklecut.atlas", "img/char/DCD_Animational/faiz/faiz_sparklecut.json",  0.8f);
    public static AnimationLoader axel_FAR_1A = new AnimationLoader(AXEL5_ATLAS,AXEL_FAR_JSON1, 0.8f);
    public static AnimationLoader axel_FAR_2A = new AnimationLoader(AXEL5_ATLAS,AXEL_FAR_JSON2, 0.8f);
    public static AnimationLoader axel_FAR_3A = new AnimationLoader(AXEL5_ATLAS,AXEL_FAR_JSON3, 0.8f);
    public static AnimationLoader axel_FAR_P = new AnimationLoader(AXEL6_ATLAS,AXEL_FAR_JSON4, 1.0f);
}

