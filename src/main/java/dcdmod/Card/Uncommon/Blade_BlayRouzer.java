package dcdmod.Card.Uncommon;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Card.Common.Blade_Beat;
import dcdmod.Card.Common.Blade_Slash;
import dcdmod.Card.SelectCard.BladeBeat_s;
import dcdmod.Card.SelectCard.BladeKick_s;
import dcdmod.Card.SelectCard.BladeMach_s;
import dcdmod.Card.SelectCard.BladeMetal_s;
import dcdmod.Card.SelectCard.BladeSlash_s;
import dcdmod.Card.SelectCard.BladeThunder_s;
import dcdmod.Card.Special.Blade_Thunder;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class Blade_BlayRouzer extends AbstractCustomCardWithType{
	
	public static final String ID = "Blade_BlayRouzer";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Blade_BlayRouzer.png";
	private static final int COST = 2;
	private boolean RouzeCard = false;
	private List<TooltipInfo> tips;
	private static CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
	private static AbstractCard c = null;
	
	public Blade_BlayRouzer() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Blade);
		this.tags.add(DCDmod.RiderCard);
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[2], EXTENDED_DESCRIPTION[3]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		int x = 0;
		int d = 0;
		if(p.hasPower("BladeSlashPower")) {
			d += AbstractDungeon.player.getPower("BladeSlashPower").amount*2;
		}
		for(AbstractPower power : p.powers) {if(power.type == PowerType.BUFF) {x++;}}
		if(d > 0 || x > 0) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, ((x*5)+d), DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			if(x>7 && p.hasPower("KamenRideBladePower") && !p.hasPower("BladeJackPower")) {
				group.addToBottom(new BladeMach_s());
				group.addToBottom(new BladeMetal_s());
				group.addToBottom(new BladeThunder_s());
				group.addToBottom(new BladeKick_s());
				group.addToBottom(new BladeBeat_s());
				group.addToBottom(new BladeSlash_s());
				AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张获得", false, false, true, false);
				RouzeCard = true;
			}
			else if(x>5 && p.hasPower("BladeJackPower")) {
				group.addToBottom(new BladeMach_s());
				group.addToBottom(new BladeMetal_s());
				group.addToBottom(new BladeThunder_s());
				group.addToBottom(new BladeKick_s());
				group.addToBottom(new BladeBeat_s());
				group.addToBottom(new BladeSlash_s());
				AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张获得", false, false, true, false);
				RouzeCard = true;
			}
		}
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
 		if(AbstractDungeon.player.hasPower("BladeSlashPower")) {
 			this.damage += AbstractDungeon.player.getPower("BladeSlashPower").amount*2;
 			this.isDamageModified = true;
 		}
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Blade_BlayRouzer();
    }
	
	@Override
	public void optionDecade() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Blade_BlayRouzer.png");
        initializeDescription();
	}

	@Override
	public void optionKuuga() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Blade_BlayRouzer.png");
        initializeDescription();
	}

	@Override
	public void optionAgito() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Blade_BlayRouzer.png");
        initializeDescription();
	}

	@Override
	public void optionRyuki() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Blade_BlayRouzer.png");
        initializeDescription();
	}

	@Override
	public void optionFaiz() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Blade_BlayRouzer.png");
        initializeDescription();
	}

	@Override
	public void optionBlade() {
		if(AbstractDungeon.player.hasPower("BladeJackPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[1];
			loadCardImage("img/cards/Blade_BlayRouzer_plus.png");
		}
		else {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
			loadCardImage("img/cards/Blade_BlayRouzer.png");
		}
        initializeDescription();
	}

	@Override
	public void optionHibiki() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Blade_BlayRouzer.png");
        initializeDescription();
	}

	@Override
	public void optionKabuto() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Blade_BlayRouzer.png");
        initializeDescription();
	}

	@Override
	public void optionDenO() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Blade_BlayRouzer.png");
        initializeDescription();
	}

	@Override
	public void optionKiva() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Blade_BlayRouzer.png");
        initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Blade_BlayRouzer.png");
        initializeDescription();
	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Blade_BlayRouzer");
        NAME = Blade_BlayRouzer.cardStrings.NAME;
        DESCRIPTION = Blade_BlayRouzer.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Blade_BlayRouzer.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(RouzeCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
			switch(AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID) {
			case "BladeBeat_s":
				c = new Blade_Beat();
				break;
			case "BladeKick_s":
				c = new Blade_Kick();
				break;
			case "BladeMach_s":
				c = new Blade_Mach();
				break;
			case "BladeMetal_s":
				c = new Blade_Metal();
				break;
			case "BladeSlash_s":
				c = new Blade_Slash();
				break;
			case "BladeThunder_s":
				c = new Blade_Thunder();
				break;
			}
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			c = null;
			group.clear();
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
			RouzeCard = false;
		}
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.UNCOMMON[0], DCDmod.UNCOMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
		}
	}

	
	
}
