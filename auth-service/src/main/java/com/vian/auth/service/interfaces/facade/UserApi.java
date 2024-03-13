package com.vian.auth.service.interfaces.facade;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.vian.auth.service.domain.user.repository.po.UserPO;
import com.vian.auth.service.domain.user.repository.facade.UserRepository;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.io.Serializable;
import java.util.List;

/**
 * 用户表 控制层。
 *
 * @author linlo
 * @since 2024-03-13
 */
@RestController
@Tag(name = "用户表接口")
@RequestMapping("/userPO")
public class UserApi {

    @Autowired
    private UserRepository userRepository;

    /**
     * 添加用户表。
     *
     * @param userPO 用户表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description="保存用户表")
    public boolean save(@RequestBody @Parameter(description="用户表")UserPO userPO) {
        return userRepository.save(userPO);
    }

    /**
     * 根据主键删除用户表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(description="根据主键用户表")
    public boolean remove(@PathVariable @Parameter(description="用户表主键")Serializable id) {
        return userRepository.removeById(id);
    }

    /**
     * 根据主键更新用户表。
     *
     * @param userPO 用户表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(description="根据主键更新用户表")
    public boolean update(@RequestBody @Parameter(description="用户表主键")UserPO userPO) {
        return userRepository.updateById(userPO);
    }

    /**
     * 查询所有用户表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(description="查询所有用户表")
    public List<UserPO> list() {
        return userRepository.list();
    }

    /**
     * 根据用户表主键获取详细信息。
     *
     * @param id 用户表主键
     * @return 用户表详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(description="根据主键获取用户表")
    public UserPO getInfo(@PathVariable Serializable id) {
        return userRepository.getById(id);
    }

    /**
     * 分页查询用户表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(description="分页查询用户表")
    public Page<UserPO> page(@Parameter(description="分页信息")Page<UserPO> page) {
        return userRepository.page(page);
    }

}
