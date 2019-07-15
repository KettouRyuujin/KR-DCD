package dcdmod.Card.Uncommon;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Card.Common.*;
import dcdmod.Card.Rare.FormRideAxel;
import dcdmod.Card.Special.Kabuto_CastOff;
import dcdmod.Card.Special.Kabuto_PutOn;
import dcdmod.Card.Status.Kabuto_Photon;
import dcdmod.Characters.Decade;
import dcdmod.FormRide.*;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.BladeJackPower;
import dcdmod.Power.JackFlightPower;
import dcdmod.Power.KabutoMaskedPower;
import dcdmod.Vfx.Axel_faiztoaxel;
import dcdmod.Vfx.Faiz_axelsounds;
import dcdmod.Vfx.Jack_henshin;
import dcdmod.Vfx.Kabuto_MaskedToRider;
import dcdmod.Vfx.Kabuto_RiderToMasked;



public class FormRide extends AbstractCustomCardWithType{
	
	public static final String ID = "FormRide";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/FormRide.png";
	private static final int COST = 2;
	AbstractCard c = null;
	CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
	boolean FormRideCard = false;
	private List<TooltipInfo> tips;
	
	public FormRide() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.FormRide);
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[13], EXTENDED_DESCRIPTION[14]));
		exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		FormRideCard = true;
		if(p.hasPower("KamenRideDecadePower")) {
			group.addToBottom(new Dragon_s());
			group.addToBottom(new Pegasus_s());
			group.addToBottom(new Titan_s());
			group.addToBottom(new Flame_s());
			group.addToBottom(new Storm_s());
			group.addToBottom(new Axel_s());
			group.addToBottom(new Jack_s());
			AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张形态卡牌获得", false, false, true, false);
		}
		else if(p.hasPower("KamenRideKuugaPower")) {
			group.addToBottom(new Dragon_s());
			group.addToBottom(new Pegasus_s());
			group.addToBottom(new Titan_s());
			AbstractDungeon.gridSelectScreen.open(group, 1, "选择1种形态切换", false, false, true, false);
		}
		else if(p.hasPower("KamenRideAgitoPower")) {
			group.addToBottom(new Flame_s());
			group.addToBottom(new Storm_s());
			AbstractDungeon.gridSelectScreen.open(group, 1, "选择1种形态切换", false, false, true, false);
		}
		else if(p.hasPower("KamenRideFaizPower")) {
			CardCrawlGame.sound.playA("formride", 0.0f);
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_faiztoaxel(p.drawX, p.drawY), 5.7F));
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_axelsounds(p.drawX, p.drawY), 0F));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 3, false), 3));
			FormRideCard = false;
		}
		else if(p.hasPower("KamenRideBladePower")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BladeJackPower(p,1),1));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new JackFlightPower(p,1),1));
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Jack_henshin(p.drawX, p.drawY), 0.0F));
			FormRideCard = false;
		}
		else if(p.hasPower("KamenRideKabutoPower")&&!p.hasPower("KabutoMaskedPower")){
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Kabuto_RiderToMasked(p.drawX - 200.00f, p.drawY + 250.00f), 1.2F));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KabutoMaskedPower(p,1),1));
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Kabuto_CastOff(), 1));
			FormRideCard = false;
		}
		else if(p.hasPower("KamenRideKabutoPower")&&p.hasPower("KabutoMaskedPower")){
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Kabuto_MaskedToRider(p.drawX - 200.00f, p.drawY + 250.00f), 1.4F));
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "KabutoMaskedPower")); 
			int[] N = {0,0,0,0,0};
			for(AbstractCard c : p.hand.group) {
				switch(c.type) {
				case ATTACK:
					N[0]++;
					break;
				case CURSE:
					N[1]++;
					AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, p.hand, true));
					break;
				case POWER:
					N[2]++;
					break;
				case SKILL:
					N[3]++;
					break;
				case STATUS:
					N[4]++;
					break;
				default:
					break;
				}
			}
			int theSize = p.hand.size();
			AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, theSize, false));
			if(N[4]!=0) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, N[4]), N[4]));
			}
			if(N[3]!=0) {
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					if ((!monster.isDead) && (!monster.isDying)) {
						AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(p, N[3]*2, DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
					}
				}
			}
			if(N[2]!=0) {
				AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Kabuto_PutOn(), 1));
			}
			if(N[1]!=0) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(p,new DamageInfo(p, N[1]*3, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				for(int i=0;i<3;i++){
					AbstractCard c = new Kabuto_Photon();
					AbstractDungeon.player.hand.moveToDeck(c, true);
				}   
			}
			if(N[0]!=0) {
				AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, N[0]));
			}
			FormRideCard = false;
		}
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.hasPower("KamenRideRyukiPower")||p.hasPower("KamenRideHibikiPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[5];
		}
		else if(p.hasPower("KamenRideFaizPower")&&EnterButtonAction.AxelForm) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[8];
		}
		else if(!p.hasPower("KamenRideDecadePower")&&!p.hasPower("KamenRideKuugaPower")&&!p.hasPower("KamenRideAgitoPower")
				&&!p.hasPower("KamenRideFaizPower")&&!p.hasPower("KamenRideBladePower")&&!p.hasPower("KamenRideHibikiPower")
				&&!p.hasPower("KamenRideKabutoPower")&&!p.hasPower("KamenRideDenOPower")&&!p.hasPower("KamenRideKivaPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		else if(p.hasPower("BladeJackPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[10];
		}
		return canUse;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new FormRide();
    }
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		if(AbstractDungeon.player instanceof Decade && AbstractDungeon.player.hasPower("KamenRideKabutoPower") && AbstractDungeon.player.hasPower("KabutoMaskedPower")){
			return this.tips;
		}
		return null;
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
		if(AbstractDungeon.player.hasPower("RisingMightyPower")||AbstractDungeon.player.hasPower("RisingPegasusPower")||AbstractDungeon.player.hasPower("RisingTitanPower")||AbstractDungeon.player.hasPower("RisingDragonPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[6];
		}
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
		this.rawDescription = EXTENDED_DESCRIPTION[7];
		initializeDescription();
	    setBackgroundTexture("img/512/attack_faiz.png", "img/1024/attack_faiz.png");
	}

	@Override
	public void optionBlade() {
		this.rawDescription = EXTENDED_DESCRIPTION[9];
		initializeDescription();
	    setBackgroundTexture("img/512/attack_blade.png", "img/1024/attack_blade.png");
	}

	@Override
	public void optionHibiki() {
		this.rawDescription = EXTENDED_DESCRIPTION[4];
		initializeDescription();
	    setBackgroundTexture("img/512/attack_hibiki.png", "img/1024/attack_hibiki.png");
	}

	@Override
	public void optionKabuto() {
		if(AbstractDungeon.player.hasPower("KabutoMaskedPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[12];
		}
		else{
			this.rawDescription = EXTENDED_DESCRIPTION[11];
		}
		initializeDescription();
	    setBackgroundTexture("img/512/attack_kabuto.png", "img/1024/attack_kabuto.png");
	}

	@Override
	public void optionDenO() {
	    setBackgroundTexture("img/512/attack_deno.png", "img/1024/attack_deno.png");
	}

	@Override
	public void optionKiva() {
	    setBackgroundTexture("img/512/attack_kiva.png", "img/1024/attack_kiva.png");
	}

	@Override
	public void optionNeutral() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
		setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeBaseCost(1);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("FormRide");
        NAME = FormRide.cardStrings.NAME;
        DESCRIPTION = FormRide.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = FormRide.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && FormRideCard) {
			switch(AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID) {
			case "Dragon_s":
				c = new FormRideDragon();
				break;
			case "Pegasus_s":
				c = new FormRidePegasus();
				break;
			case "Titan_s":
				c = new FormRideTitan();
				break;
			case "Flame_s":
				c = new FormRideFlame();
				break;
			case "Storm_s":
				c = new FormRideStorm();
				break;
			case "Axel_s":
				c = new FormRideAxel();
				break;
			case "Jack_s":
				c = new FormRideJack();
				break;
			}
			if(AbstractDungeon.player.hasPower("KamenRideDecadePower")) {
				c.freeToPlayOnce = true;
				AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			}
			else {
				c.freeToPlayOnce = true;
				c.purgeOnUse = true;
				AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, null, c.energyOnUse));
			}
			c = null;
			FormRideCard = false;
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
