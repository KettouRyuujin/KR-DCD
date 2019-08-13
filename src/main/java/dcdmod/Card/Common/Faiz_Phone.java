package dcdmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.Phone_Mark;
import dcdmod.Vfx.Faiz_gunattack;



public class Faiz_Phone extends AbstractCustomCardWithType{
	
	public static final String ID = "Faiz_Phone";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Faiz_Phone.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 3;
	private static final int MAGIC_NUM = 3;
	private List<TooltipInfo> tips;

	public Faiz_Phone() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY,CardColorType.Faiz);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[3], EXTENDED_DESCRIPTION[4]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		int x = 3;
		if(upgraded) {
			x = 4;
		}
		for(int i=0;i<this.magicNumber;i++) {
			AbstractMonster monster = AbstractDungeon.getMonsters().getRandomMonster(true);
			AbstractDungeon.actionManager.addToTop(new VFXAction(p, new CleaveEffect(), 0.0F));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(p, x,  DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			if(p.hasPower("KamenRideFaizPower")  && !monster.hasPower("Phone_Mark")) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new Phone_Mark(p, 1), 1));
			}
		}
		if(p.hasPower("KamenRideFaizPower")) {
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_gunattack(), 0F));
		}
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Faiz_Phone();
    }
	
	@Override
	public void optionDecade() {
		this.rawDescription = DESCRIPTION;
		if(upgraded) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
        initializeDescription();
	}

	@Override
	public void optionKuuga() {
		this.rawDescription = DESCRIPTION;
		if(upgraded) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
        initializeDescription();
	}

	@Override
	public void optionAgito() {
		this.rawDescription = DESCRIPTION;
		if(upgraded) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
        initializeDescription();
	}

	@Override
	public void optionRyuki() {
		this.rawDescription = DESCRIPTION;
		if(upgraded) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
        initializeDescription();
	}

	@Override
	public void optionFaiz() {
		this.rawDescription = EXTENDED_DESCRIPTION[1];
		if(upgraded) {
			this.rawDescription = EXTENDED_DESCRIPTION[2];
		}
        initializeDescription();
	}

	@Override
	public void optionBlade() {
		this.rawDescription = DESCRIPTION;
		if(upgraded) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
        initializeDescription();
	}

	@Override
	public void optionHibiki() {
		this.rawDescription = DESCRIPTION;
		if(upgraded) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
        initializeDescription();
	}

	@Override
	public void optionKabuto() {
		this.rawDescription = DESCRIPTION;
		if(upgraded) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
        initializeDescription();
	}

	@Override
	public void optionDenO() {
		this.rawDescription = DESCRIPTION;
		if(upgraded) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
        initializeDescription();
	}

	@Override
	public void optionKiva() {
		this.rawDescription = DESCRIPTION;
		if(upgraded) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
        initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.rawDescription = DESCRIPTION;
		if(upgraded) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
        initializeDescription();
	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
            this.rawDescription = EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Faiz_Phone");
        NAME = Faiz_Phone.cardStrings.NAME;
        DESCRIPTION = Faiz_Phone.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Faiz_Phone.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.COMMON[0], DCDmod.COMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
		}
	}

	
	
}
