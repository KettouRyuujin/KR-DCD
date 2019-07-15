package dcdmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Card.Common.*;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class AttackRide extends AbstractCustomCardWithType{
	
	public static final String ID = "AttackRide";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/AttackRide.png";
	private static final int COST = 1;
	AbstractCard c = null;
	CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
	boolean AttackRideCard = false;
	
	public AttackRide() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AttackRideCard = true;
		if(p.hasPower("KamenRideDecadePower")) {
			group.addToBottom(new Kabuto_KunaiGun());
			group.addToBottom(new Hibiki_Ongekibou());
			group.addToBottom(new Blade_BlayRouzer());
			group.addToBottom(new Faiz_Edge());
			group.addToBottom(new Faiz_Shot());
			group.addToBottom(new Faiz_Phone());
			group.addToBottom(new Ryuki_DragSaber());
			group.addToBottom(new Agito_StormHalberd());
			group.addToBottom(new Agito_FlameSaber());
			group.addToBottom(new Kuuga_TitanSword());
			group.addToBottom(new Kuuga_PegasusBowgun());
			group.addToBottom(new Kuuga_DragonRod());
			AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张卡牌获得", false, false, true, false);
		}
		else if(p.hasPower("KamenRideKuugaPower")) {
			group.addToBottom(new Kuuga_TitanSword());
			group.addToBottom(new Kuuga_DragonRod());
			group.addToBottom(new Kuuga_PegasusBowgun());
			AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张卡牌获得", false, false, true, false);
		}
		else if(p.hasPower("KamenRideAgitoPower")) {
			group.addToBottom(new Agito_FlameSaber());
			group.addToBottom(new Agito_StormHalberd());
			AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张卡牌获得", false, false, true, false);
		}
		else if(p.hasPower("KamenRideRyukiPower")) {
			c = new Ryuki_DragSaber();
			c.freeToPlayOnce = true;
			if(upgraded){
				c.upgrade();
			}
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			AttackRideCard = false;
		}
		else if(p.hasPower("KamenRideFaizPower")) {
			group.addToBottom(new Faiz_Edge());
			group.addToBottom(new Faiz_Phone());
			group.addToBottom(new Faiz_Shot());
			AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张卡牌获得", false, false, true, false);
		}
		else if(p.hasPower("KamenRideBladePower")) {
			c = new Blade_BlayRouzer();
			c.freeToPlayOnce = true;
			if(upgraded){
				c.upgrade();
			}
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			AttackRideCard = false;
		}
		else if(p.hasPower("KamenRideHibikiPower")) {
			c = new Hibiki_Ongekibou();
			c.freeToPlayOnce = true;
			if(upgraded){
				c.upgrade();
			}
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			AttackRideCard = false;
		}
		else if(p.hasPower("KamenRideKabutoPower")){
			c = new Kabuto_KunaiGun();
			c.freeToPlayOnce = true;
			if(upgraded){
				c.upgrade();
			}
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			AttackRideCard = false;
		}
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(!p.hasPower("KamenRideDecadePower")&&!p.hasPower("KamenRideKuugaPower")&&!p.hasPower("KamenRideAgitoPower")
				&&!p.hasPower("KamenRideRyukiPower")&&!p.hasPower("KamenRideFaizPower")&&!p.hasPower("KamenRideBladePower")
				&&!p.hasPower("KamenRideHibikiPower")&&!p.hasPower("KamenRideKabutoPower")&&!p.hasPower("KamenRideDenOPower")
				&&!p.hasPower("KamenRideKivaPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new AttackRide();
    }
	
	@Override
	public void optionDecade() {
		this.rawDescription = EXTENDED_DESCRIPTION[1];
		initializeDescription();
		setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
	}

	@Override
	public void optionKuuga() {
		this.rawDescription = EXTENDED_DESCRIPTION[2];
		initializeDescription();
		setBackgroundTexture("img/512/attack_kuuga.png", "img/1024/attack_kuuga.png");
	}

	@Override
	public void optionAgito() {
		this.rawDescription = EXTENDED_DESCRIPTION[3];
		initializeDescription();
	    setBackgroundTexture("img/512/attack_agito.png", "img/1024/attack_agito.png");
	}

	@Override
	public void optionRyuki() {
		this.rawDescription = EXTENDED_DESCRIPTION[4];
		initializeDescription();
	    setBackgroundTexture("img/512/attack_ryuki.png", "img/1024/attack_ryuki.png");
	}

	@Override
	public void optionFaiz() {
		this.rawDescription = EXTENDED_DESCRIPTION[5];
		initializeDescription();
	    setBackgroundTexture("img/512/attack_faiz.png", "img/1024/attack_faiz.png");
	}

	@Override
	public void optionBlade() {
		this.rawDescription = EXTENDED_DESCRIPTION[6];
		initializeDescription();
	    setBackgroundTexture("img/512/attack_blade.png", "img/1024/attack_blade.png");
	}

	@Override
	public void optionHibiki() {
		this.rawDescription = EXTENDED_DESCRIPTION[7];
		initializeDescription();
	    setBackgroundTexture("img/512/attack_hibiki.png", "img/1024/attack_hibiki.png");
	}

	@Override
	public void optionKabuto() {
		this.rawDescription = EXTENDED_DESCRIPTION[8];
		initializeDescription();
	    setBackgroundTexture("img/512/attack_kabuto.png", "img/1024/attack_kabuto.png");
	}

	@Override
	public void optionDenO() {
		this.rawDescription = EXTENDED_DESCRIPTION[9];
		initializeDescription();
	    setBackgroundTexture("img/512/attack_deno.png", "img/1024/attack_deno.png");
	}

	@Override
	public void optionKiva() {
		this.rawDescription = EXTENDED_DESCRIPTION[10];
		initializeDescription();
	    setBackgroundTexture("img/512/attack_kiva.png", "img/1024/attack_kiva.png");
	}

	@Override
	public void optionNeutral() {
		this.rawDescription = DESCRIPTION;
		if(upgraded){
			this.rawDescription = UPGRADE_DESCRIPTION;
		}
		initializeDescription();
		setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("AttackRide");
        NAME = AttackRide.cardStrings.NAME;
        DESCRIPTION = AttackRide.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = AttackRide.cardStrings.EXTENDED_DESCRIPTION;
        UPGRADE_DESCRIPTION = AttackRide.cardStrings.UPGRADE_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && AttackRideCard) {
			c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
			c.freeToPlayOnce = true;
			if(upgraded){
				c.upgrade();
			}
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			c = null;
			AttackRideCard = false;
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
			group.clear();
		}
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.UNCOMMON[0], DCDmod.UNCOMMON_P[0]);
		}
		else if(this.costForTurn == -1) {
			setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
		}
	}

	
	
}
