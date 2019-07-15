 package dcdmod.Patches;


 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
 
 public abstract class AbstractCustomCardWithType extends AbstractGIFCard
 {
	 public CardColorType colorType;
	 private boolean willApplyPowers;
   
   public static enum CardColorType
   {
     Decade, Kuuga, Agito, Ryuki, Faiz, Blade, Hibiki, Kabuto, DenO, Kiva;
     
 
     private CardColorType() {}
   }
   
   public AbstractCustomCardWithType(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, CardColorType damageType, boolean willApplyPowers)
   {
     super(id, name, img, cost, rawDescription, type, color, rarity, target);
     
	   this.willApplyPowers = willApplyPowers;
	   this.colorType = damageType;
  
	   assignOrbTexture();
	   assignBGTexture();
	   assignBannerTexture();
   }

			public AbstractCustomCardWithType(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, CardColorType damageType) {
     this(id, name, img, cost, rawDescription, type, color, rarity, target, damageType, true);
   }
   
 
   private void assignBannerTexture() {
	   switch (this.rarity){
		case BASIC:
			if(this.freeToPlayOnce){
				setBannerTexture(DCDmod.BASIC[0], DCDmod.BASIC_P[0]);
			}
			else if(this.cost == -1) {
				setBannerTexture(DCDmod.BASIC[6], DCDmod.BASIC_P[6]);
			}
			else {
				int cost = this.cost;
				setBannerTexture(DCDmod.BASIC[cost], DCDmod.BASIC_P[cost]);
			}
			if(this.costForTurn == -1) {
				setBannerTexture(DCDmod.BASIC[6], DCDmod.BASIC_P[6]);
			}
			else {
				int cost = this.costForTurn;
				setBannerTexture(DCDmod.BASIC[cost], DCDmod.BASIC_P[cost]);
			}
			break;
		case COMMON:
			if(this.freeToPlayOnce){
				setBannerTexture(DCDmod.COMMON[0], DCDmod.COMMON_P[0]);
			}
			else if(this.cost == -1) {
				setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
			}
			else {
				int cost = this.cost;
				setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
			}
			if(this.costForTurn == -1) {
				setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
			}
			else {
				int cost = this.costForTurn;
				setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
			}
			break;
		case CURSE:
			break;
		case RARE:
			if(this.freeToPlayOnce){
				setBannerTexture(DCDmod.RARE[0], DCDmod.RARE_P[0]);
			}
			else if(this.cost == -1) {
				setBannerTexture(DCDmod.RARE[6], DCDmod.RARE_P[6]);
			}
			else {
				int cost = this.cost;
				setBannerTexture(DCDmod.RARE[cost], DCDmod.RARE_P[cost]);
			}
			if(this.costForTurn == -1) {
				setBannerTexture(DCDmod.RARE[6], DCDmod.RARE_P[6]);
			}
			else {
				int cost = this.costForTurn;
				setBannerTexture(DCDmod.RARE[cost], DCDmod.RARE_P[cost]);
			}
			break;
		case SPECIAL:
			if(this.freeToPlayOnce){
				setBannerTexture(DCDmod.SPECIAL[0], DCDmod.SPECIAL_P[0]);
			}
			else if(this.cost == -1) {
				setBannerTexture(DCDmod.SPECIAL[6], DCDmod.SPECIAL_P[6]);
			}
			else {
				int cost = this.cost;
				setBannerTexture(DCDmod.SPECIAL[cost], DCDmod.SPECIAL_P[cost]);
			}
			if(this.costForTurn == -1) {
				setBannerTexture(DCDmod.SPECIAL[6], DCDmod.SPECIAL_P[6]);
			}
			else {
				int cost = this.costForTurn;
				setBannerTexture(DCDmod.SPECIAL[cost], DCDmod.SPECIAL_P[cost]);
			}
			break;
		case UNCOMMON:
			if(this.freeToPlayOnce){
				setBannerTexture(DCDmod.UNCOMMON[0], DCDmod.UNCOMMON_P[0]);
			}
			else if(this.cost == -1) {
				setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
			}
			else {
				int cost = this.cost;
				setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
			}
			if(this.costForTurn == -1) {
				setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
			}
			else {
				int cost = this.costForTurn;
				setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
			}
			break;
		default:
			break;
		   
	   }
	   if(this.cardID == "FinalAttackRide"){
			if(this.freeToPlayOnce){
				setBannerTexture(DCDmod.FAR[0], DCDmod.FAR_P[0]);
			}
			else if(this.cost == -1) {
				setBannerTexture(DCDmod.FAR[6], DCDmod.FAR_P[6]);
			}
			else {
				int cost = this.cost;
				setBannerTexture(DCDmod.FAR[cost], DCDmod.FAR_P[cost]);
			}
			if(this.costForTurn == -1) {
				setBannerTexture(DCDmod.FAR[6], DCDmod.FAR_P[6]);
			}
			else {
				int cost = this.costForTurn;
				setBannerTexture(DCDmod.FAR[cost], DCDmod.FAR_P[cost]);
			}
	   }
   }
   
   private void assignOrbTexture()
   {
     switch (this.colorType) {
     case Decade: 
       setOrbTexture("img/512/orb-dark.png", "img/1024/orb-dark.png");
       break;
     case Kuuga: 
       setOrbTexture("img/512/orb-dark.png", "img/1024/orb-dark.png");
       break;
     case Agito: 
       setOrbTexture("img/512/orb-dark.png", "img/1024/orb-dark.png");
       break;
     case Ryuki: 
       setOrbTexture("img/512/orb-dark.png", "img/1024/orb-dark.png");
       break;
     case Faiz: 
       setOrbTexture("img/512/orb-dark.png", "img/1024/orb-dark.png");
       break;
     case Blade: 
       setOrbTexture("img/512/orb-dark.png", "img/1024/orb-dark.png");
       break;
     case Hibiki: 
       setOrbTexture("img/512/orb-dark.png", "img/1024/orb-dark.png");
       break;
     case Kabuto: 
       setOrbTexture("img/512/orb-dark.png", "img/1024/orb-dark.png");
       break;
     case DenO: 
       setOrbTexture("img/512/orb-dark.png", "img/1024/orb-dark.png");
       break;
     case Kiva: 
       setOrbTexture("img/512/orb-dark.png", "img/1024/orb-dark.png");
     }
     
   }
   
 
   private void assignBGTexture()
   {
     int indexPointer = 0;
     String[] portraitListPointer = null; 
     String[] textureListPointer = null; 
     switch (this.type) {
     	case ATTACK: 
     		textureListPointer = DCDmod.ATTACK_BG;
     		portraitListPointer = DCDmod.ATTACK_BG_P;
     		break;
     	case SKILL: 
     		textureListPointer = DCDmod.SKILL_BG;
     		portraitListPointer = DCDmod.SKILL_BG_P;
     		break;
     	case POWER: 
     		textureListPointer = DCDmod.POWER_BG;
     		portraitListPointer = DCDmod.POWER_BG_P;
     		break;
     	default:
     		break;
     }
     
     
     switch (this.colorType) {
     	case Decade: 
     		indexPointer = 0;
     		break;
     	case Kuuga: 
     		indexPointer = 1;
     		break;
     	case Agito: 
     		indexPointer = 2;
     		break;
     	case Ryuki:
     		indexPointer = 3;
    	 	break;
     	case Faiz:
     		indexPointer = 4;
     		break;
     	case Blade:
     		indexPointer = 5;
     		break;
     	case Hibiki:
     		indexPointer = 6;
     		break;
     	case Kabuto:
     		indexPointer = 7;
     		break;
     	case DenO:
     		indexPointer = 8;
     		break;
     	case Kiva:
     		indexPointer = 9;
     		break;
     	default:
     		indexPointer = 0;
     		break;
     }
            setBackgroundTexture(textureListPointer[indexPointer], portraitListPointer[indexPointer]);
 }
 
			public void calculateCardDamage(AbstractMonster arg0)
   {
     super.calculateCardDamage(arg0);
     
     int modifier = 0;
     if (!this.willApplyPowers) {
       return;
     }
     if (this.damage != 0)
     {
    	 switch (this.colorType) {
    	 	case Decade: 
    	 		if(AbstractDungeon.player.hasPower("BladeThunderPower") && this.cardID != "FinalAttackRide") {
    	 			modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
    	 		}
    	 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
    	 			modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
    	 		}
    	 		break;
    	 	case Kuuga:
    	 		if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
    	 			modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
    	 		}
    	 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
    	 			modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
    	 		}
    	 		break;
    	 	case Agito:
    	 		if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
    	 			modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
	    		}
    	 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
    	 			modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
    	 		}
	      		break;
	       	case Ryuki:
	      		if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
	      			modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
	    		}
    	 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
    	 			modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
    	 		}
	      		break;
	       	case Faiz:
	      		if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
	      			modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
	    		}
    	 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
    	 			modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
    	 		}
	      		break;
	       	case Blade:
	      		if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
	      			modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
	    		}
    	 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
    	 			modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
    	 		}
	      		break;
	       	case Hibiki:
	      		if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
	      			modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
	    		}
    	 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
    	 			modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
    	 		}
	      		break;
	       	case Kabuto:
	      		if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
	      			modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
	    		}
    	 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
    	 			modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
    	 		}
	      		break;
	       	case DenO:
	      		if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
	      			modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
	    		}
    	 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
    	 			modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
    	 		}
	      		break;
	       	case Kiva:
	      		if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
	      			modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
	    		}
    	 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
    	 			modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
    	 		}
	      		break;		  
	       	default:
	      		if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
	      			modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
	    		}
    	 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
    	 			modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
    	 		}
	      		break;       
    	 }
				
				if (modifier != 0) { 
					this.isDamageModified = true;		
       }
       
				this.damage += modifier;
       
       if (this.isMultiDamage) {
         for (int i = 0; i < this.multiDamage.length; i++) {
           this.multiDamage[i] += modifier;
         }
       }
     }
   }
			
			
			public abstract void energychange();
			
			
			public void update() {
				super.update();
						energychange();
			 }
			   
			   public abstract void optionDecade();

			   public abstract void optionKuuga();

			   public abstract void optionAgito();

			   public abstract void optionRyuki();

			   public abstract void optionFaiz();

			   public abstract void optionBlade();

			   public abstract void optionHibiki();

			   public abstract void optionKabuto();

			   public abstract void optionDenO();

			   public abstract void optionKiva();

			   public abstract void optionNeutral();

			   public void applyPowers() {
			   	super.applyPowers();
			   	if (AbstractDungeon.player.hasPower("KamenRideDecadePower")) {
			    	 	optionDecade();
			   	} 
			   	else if (AbstractDungeon.player.hasPower("KamenRideKuugaPower")) {
			    	 	optionKuuga();
			   	} 
			   	else if(AbstractDungeon.player.hasPower("KamenRideAgitoPower")){
			    	 	optionAgito();
			   	}
			   	else if(AbstractDungeon.player.hasPower("KamenRideRyukiPower")){
			    	 	optionRyuki();
			   	}
			   	else if(AbstractDungeon.player.hasPower("KamenRideFaizPower")){
			   	 		optionFaiz();
			   	}
			   	else if(AbstractDungeon.player.hasPower("KamenRideBladePower")){
			   	 		optionBlade();
			   	}
			   	else if(AbstractDungeon.player.hasPower("KamenRideHibikiPower")){
			   	 		optionHibiki();
			   	}
			   	else if(AbstractDungeon.player.hasPower("KamenRideKabutoPower")){
			   	 		optionKabuto();
			   	}
			   	else if(AbstractDungeon.player.hasPower("KamenRideDenOPower")){
			   	 		optionDenO();
			   	}
			   	else if(AbstractDungeon.player.hasPower("KamenRideKivaPower")){
			   	 		optionKiva();
			   	}
			   	else {
			   			optionNeutral();
			   	}
			   }
			
   
 }


