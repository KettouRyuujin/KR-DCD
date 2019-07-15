package dcdmod.Card.Special;

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
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Card.Status.Kabuto_Photon;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Vfx.Kabuto_MaskedToRider;



public class Kabuto_CastOff extends AbstractCustomCardWithType{
	
	public static final String ID = "Kabuto_CastOff";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Kabuto_CastOff.png";
	private static final int COST = 0;
	private List<TooltipInfo> tips;
	
	
	public Kabuto_CastOff() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF,CardColorType.Kabuto);
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.KamenRide);
		this.exhaust = true;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
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
				AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, p.hand, true));
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
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Kabuto_CastOff();
    }
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(!p.hasPower("KabutoMaskedPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	@Override
	public void optionDecade() {
		this.damageType = DamageType.NORMAL;
	}

	@Override
	public void optionKuuga() {
		
	}

	@Override
	public void optionAgito() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionRyuki() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionFaiz() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionBlade() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionHibiki() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKabuto() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionDenO() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKiva() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionNeutral() {
		this.damageType = DamageType.NORMAL;
	}
    
	@Override
    public void upgrade() {
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Kabuto_CastOff");
        NAME = Kabuto_CastOff.cardStrings.NAME;
        DESCRIPTION = Kabuto_CastOff.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Kabuto_CastOff.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.SPECIAL[0], DCDmod.SPECIAL_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.SPECIAL[6], DCDmod.SPECIAL_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.SPECIAL[cost], DCDmod.SPECIAL_P[cost]);
		}
	}

	
	
}
