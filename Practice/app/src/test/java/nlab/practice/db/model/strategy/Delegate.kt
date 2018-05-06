package nlab.practice.db.model.strategy

/**
 * Delegate 패턴.
 *
 * 여러 인터페이스에 대한 구현을 has-a 관계로 가짐으로, 상속의 한계를 넘을 수 있음.
 */
class Delegate(flyable: Flyable, jumpAble: JumpAble) : Flyable by flyable, JumpAble by jumpAble