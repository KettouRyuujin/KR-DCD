package dcdmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.powers.DexterityPower;

import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.Decade_Dash1Power;



public class Decade_Dash1 extends AbstractCustomCardWithType{
	
	public static final String ID = "Decade_Dash1";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Decade_Dash1.png";
	private static final int COST = 1;
	private List<TooltipInfo> tips;
	
	
	public Decade_Dash1() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = 1;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[2], EXTENDED_DESCRIPTION[1]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Decade_Dash1Power(p, m, this.magicNumber), this.magicNumber));
		AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, -2), -2));
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(m != null && m.intent != Intent.ATTACK && m.intent != Intent.ATTACK_BUFF && m.intent != Intent.ATTACK_DEBUFF && m.intent != Intent.ATTACK_DEFEND) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Decade_Dash1();
    }
	
	@Override
	public void optionDecade() {
		setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
	}

	@Override
	public void optionKuuga() {
	     setBackgroundTexture("img/512/skill_kuuga.png", "img/1024/skill_kuuga.png");
	}

	@Override
	public void optionAgito() {
	    setBackgroundTexture("img/512/skill_agito.png", "img/1024/skill_agito.png");
	}

	@Override
	public void optionRyuki() {
	    setBackgroundTexture("img/512/skill_ryuki.png", "img/1024/skill_ryuki.png");
	}

	@Override
	public void optionFaiz() {
	    setBackgroundTexture("img/512/skill_faiz.png", "img/1024/skill_faiz.png");
	}

	@Override
	public void optionBlade() {
	    setBackgroundTexture("img/512/skill_blade.png", "img/1024/skill_blade.png");
	}

	@Override
	public void optionHibiki() {
	    setBackgroundTexture("img/512/skill_hibiki.png", "img/1024/skill_hibiki.png");
	}

	@Override
	public void optionKabuto() {
	    setBackgroundTexture("img/512/skill_kabuto.png", "img/1024/skill_kabuto.png");
	}

	@Override
	public void optionDenO() {
	    setBackgroundTexture("img/512/skill_deno.png", "img/1024/skill_deno.png");
	}

	@Override
	public void optionKiva() {
	    setBackgroundTexture("img/512/skill_kiva.png", "img/1024/skill_kiva.png");
	}

	@Override
	public void optionNeutral() {
		setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeBaseCost(0);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Decade_Dash1");
        NAME = Decade_Dash1.cardStrings.NAME;
        DESCRIPTION = Decade_Dash1.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Decade_Dash1.cardStrings.EXTENDED_DESCRIPTION;
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
