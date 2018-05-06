package nlab.practice.config

import nlab.practice.db.model.erp.MemberOrganizationVO
import nlab.practice.db.model.erp.MemberVO
import nlab.practice.db.model.erp.OrganizationVO

/**
 * 구성원 정보에 [organizationVO] 를 추가하여, MemberOrganizationVO 를 생산.
 *
 * 확장 함수 정의.
 *
 * - 함수 위치가 클래스 내에 속하지 않는다면 전역적으로 사용 가능.
 * - 클래스 내에 존재한다면, 해당 클래스 내에서만 사용 가능.
 * @param organizationVO
 */
fun MemberVO.toMemberOrganizationVO(organizationVO: OrganizationVO) : MemberOrganizationVO
        = MemberOrganizationVO(
            this.memberSn,
            organizationVO.organizationSn,
            this.name,
            organizationVO.organizationName)