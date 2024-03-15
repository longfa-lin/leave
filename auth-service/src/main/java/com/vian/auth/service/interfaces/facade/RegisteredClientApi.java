//package com.vian.auth.service.interfaces.facade;
//
//import com.mybatisflex.core.paginate.Page;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.vian.auth.service.domain.user.repository.po.RegisteredClientPO;
//import com.vian.auth.service.domain.user.repository.facade.RegisteredClientRepository;
//import org.springframework.web.bind.annotation.RestController;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import java.io.Serializable;
//import java.util.List;
//
///**
// * oauth2 客户端注册登记 控制层。
// *
// * @author linlo
// * @since 2024-03-13
// */
//@RestController
//@Tag(name = "oauth2 客户端注册登记接口")
//@RequestMapping("/registeredClientPO")
//public class RegisteredClientApi {
//
//    @Autowired
//    private RegisteredClientRepository registeredClientRepository;
//
//    /**
//     * 添加oauth2 客户端注册登记。
//     *
//     * @param registeredClientPO oauth2 客户端注册登记
//     * @return {@code true} 添加成功，{@code false} 添加失败
//     */
//    @PostMapping("save")
//    @Operation(description="保存oauth2 客户端注册登记")
//    public boolean save(@RequestBody @Parameter(description="oauth2 客户端注册登记")RegisteredClientPO registeredClientPO) {
//        return registeredClientRepository.save(registeredClientPO);
//    }
//
//    /**
//     * 根据主键删除oauth2 客户端注册登记。
//     *
//     * @param id 主键
//     * @return {@code true} 删除成功，{@code false} 删除失败
//     */
//    @DeleteMapping("remove/{id}")
//    @Operation(description="根据主键oauth2 客户端注册登记")
//    public boolean remove(@PathVariable @Parameter(description="oauth2 客户端注册登记主键")Serializable id) {
//        return registeredClientRepository.removeById(id);
//    }
//
//    /**
//     * 根据主键更新oauth2 客户端注册登记。
//     *
//     * @param registeredClientPO oauth2 客户端注册登记
//     * @return {@code true} 更新成功，{@code false} 更新失败
//     */
//    @PutMapping("update")
//    @Operation(description="根据主键更新oauth2 客户端注册登记")
//    public boolean update(@RequestBody @Parameter(description="oauth2 客户端注册登记主键")RegisteredClientPO registeredClientPO) {
//        return registeredClientRepository.updateById(registeredClientPO);
//    }
//
//    /**
//     * 查询所有oauth2 客户端注册登记。
//     *
//     * @return 所有数据
//     */
//    @GetMapping("list")
//    @Operation(description="查询所有oauth2 客户端注册登记")
//    public List<RegisteredClientPO> list() {
//        return registeredClientRepository.list();
//    }
//
//    /**
//     * 根据oauth2 客户端注册登记主键获取详细信息。
//     *
//     * @param id oauth2 客户端注册登记主键
//     * @return oauth2 客户端注册登记详情
//     */
//    @GetMapping("getInfo/{id}")
//    @Operation(description="根据主键获取oauth2 客户端注册登记")
//    public RegisteredClientPO getInfo(@PathVariable Serializable id) {
//        return registeredClientRepository.getById(id);
//    }
//
//    /**
//     * 分页查询oauth2 客户端注册登记。
//     *
//     * @param page 分页对象
//     * @return 分页对象
//     */
//    @GetMapping("page")
//    @Operation(description="分页查询oauth2 客户端注册登记")
//    public Page<RegisteredClientPO> page(@Parameter(description="分页信息")Page<RegisteredClientPO> page) {
//        return registeredClientRepository.page(page);
//    }
//
//}
