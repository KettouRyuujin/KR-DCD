package dcdmod.Card.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Vfx.RiderBooker_shoot;


public class RideBooker_Shoot extends AbstractCustomCardWithType{
	
	public static final String ID = "RideBooker_Shoot";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/RideBooker_Shoot.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;

	
	
	public RideBooker_Shoot() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToTop(new VFXAction(new RiderBooker_shoot(), 0F));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("BladeSlashPower")) {
			int x;
			x = AbstractDungeon.player.getPower("BladeSlashPower").amount * 2;
			this.damage += x;
			this.isDamageModified = true;
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new RideBooker_Shoot();
    }
	
	@Override
	public void optionDecade() {
		this.damageType = DamageType.NORMAL;
	}

	@Override
	public void optionKuuga() {
		if(AbstractDungeon.player.hasPower("KuugaPegasusPower")||AbstractDungeon.player.hasPower("RisingPegasusPower")) {
			this.damageType = DamageType.HP_LOSS;
		}
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("RideBooker_Shoot");
        NAME = RideBooker_Shoot.cardStrings.NAME;
        DESCRIPTION = RideBooker_Shoot.cardStrings.DESCRIPTION;
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
